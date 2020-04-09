package com.example.learndayandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learndayandroid.fragment.TabFragment;
import com.example.learndayandroid.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description 主界面
 *
 * @author skytofire
 * @date 2020/04/09
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Button mWeChatBtn;
    private Button mContactBtn;
    private Button mFindBtn;
    private Button mMine;
    private List<String> mTitleList = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));
    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        initOnClick();
    }

    private void initAdapter() {
        /**
         * FragmentPagerAdapter(滑动调用onDestoryView和onCreateView Fragment并没有被销毁) 用于少量tab切换
         * FragmentStatePagerAdapter(滑动调用onDestoryView、onCreateView、onCreate、onDestory Fragment会被销毁)用于众多fragment切换
         */
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 1) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                TabFragment fragment = TabFragment.newInstance(mTitleList.get(position));
                fragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                    @Override
                    public void onClick(String title) {
                        mWeChatBtn.setText(title);
                    }
                });
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment tabFragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position, tabFragment);
                return tabFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //左到右 0-1 left:pos right:pos +1 position 0~1
                //右到左 1-0 left:pos right:pos + 1 position 1~0
                L.d("onPageScrolled " + position + "positionOffset" + positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initOnClick() {
        mWeChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabFragment fragment = mFragments.get(0);
                if (fragment != null) {
                    fragment.changeTitle("微信changed");
                }
            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_main);
        mWeChatBtn = findViewById(R.id.btn_main_chat);
        mContactBtn = findViewById(R.id.btn_main_contact);
        mFindBtn = findViewById(R.id.btn_main_find);
        mMine = findViewById(R.id.btn_main_mine);
        mViewPager.setOffscreenPageLimit(mTitleList.size());
    }
}
