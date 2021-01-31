package com.example.learndayandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.learndayandroid.R;
import com.example.learndayandroid.transformer.ScaleTransformer;
import com.example.learndayandroid.transformer.ScrollTransformer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author By skytofire
 * @explain 创建说明
 * @date 创建时间:2020/4/11.
 */
public class SplashActivity extends AppCompatActivity {

    private ViewPager mViewScale, mViewScroll;
    private int[] mResIds = new int[]{0xffff0000, 0xff00ff00, 0xff0000ff};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mViewScale = findViewById(R.id.viewPager_scale);
        mViewScroll = findViewById(R.id.viewPager_scroll);
        initViewPagerAdapter(mViewScale);
        initViewPagerAdapter(mViewScroll);
        //缩放及透明度动画
        mViewScale.setPageTransformer(true, new ScaleTransformer());
        mViewScroll.setPageMargin(20);
        //旋转测试动画
        mViewScroll.setPageTransformer(true, new ScrollTransformer());
    }

    private void initViewPagerAdapter(ViewPager mView) {
        mView.setOffscreenPageLimit(mResIds.length);
        mView.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = new View(container.getContext());
                view.setBackgroundColor(mResIds[position]);
                view.setTag(mResIds[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
    }
}
