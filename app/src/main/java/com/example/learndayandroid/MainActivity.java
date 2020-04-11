package com.example.learndayandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learndayandroid.fragment.TabFragment;
import com.example.learndayandroid.utils.L;
import com.example.learndayandroid.view.TableView;

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
    private TableView mWeChatBtn;
    private TableView mContactBtn;
    private TableView mFindBtn;
    private TableView mMine;
    private List<String> mTitleList = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));
    private SparseArray<TabFragment> mFragments = new SparseArray<>();
    private List<TableView> mBtnList = new ArrayList<>();
    private static final String BUNDLE_KEY_SELECT = "bundle_key_select";
    private int select = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            select = savedInstanceState.getInt(BUNDLE_KEY_SELECT, 0);
        }
        initView();
        initAdapter();
        initOnClick();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //保存当前tab选中状态
        outState.putInt(BUNDLE_KEY_SELECT, select);
        super.onSaveInstanceState(outState);
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
                return TabFragment.newInstance(mTitleList.get(position));
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //添加fragment
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
                if (position >=0 && position < mBtnList.size() -1) {
                    mBtnList.get(position).setProgress(1 - positionOffset);
                    mBtnList.get(position + 1).setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected " + position);
                select = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initOnClick() {
        for (int i = 0; i < mBtnList.size(); i++) {
            final int finalI = i;
            mBtnList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置当前选择的界面
                    mViewPager.setCurrentItem(finalI, false);
                    setCurrentTab(finalI);
                    select = finalI;
                }
            });
        }

    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_main);
        mWeChatBtn = findViewById(R.id.btn_main_chat);
        mContactBtn = findViewById(R.id.btn_main_contact);
        mFindBtn = findViewById(R.id.btn_main_find);
        mMine = findViewById(R.id.btn_main_mine);
        mViewPager.setOffscreenPageLimit(mTitleList.size());

        mWeChatBtn.changeIconAndProgress(R.drawable.icon_home_pager_not_selected, R.drawable.icon_home_pager_selected, mTitleList.get(0));
        mContactBtn.changeIconAndProgress(R.drawable.icon_me_not_selected, R.drawable.icon_me_selected, mTitleList.get(1));
        mFindBtn.changeIconAndProgress(R.drawable.icon_navigation_not_selected, R.drawable.icon_navigation_selected, mTitleList.get(2));
        mMine.changeIconAndProgress(R.drawable.icon_project_not_selected, R.drawable.icon_project_selected, mTitleList.get(3));

        mBtnList.add(mWeChatBtn);
        mBtnList.add(mContactBtn);
        mBtnList.add(mFindBtn);
        mBtnList.add(mMine);

        setCurrentTab(select);
    }

    /**
     * 设置选择的tab变色，其他tab复原
     * @param select
     */
    private void setCurrentTab(int select) {
        for (int i = 0; i < mBtnList.size(); i++) {
            if (i == select){
                mBtnList.get(i).setProgress(1);
            }else {
                mBtnList.get(i).setProgress(0);
            }
        }
    }


}
