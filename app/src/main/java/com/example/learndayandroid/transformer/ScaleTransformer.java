package com.example.learndayandroid.transformer;

import android.view.View;

import com.example.learndayandroid.utils.L;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author By skytofire
 * @explain viewpager的缩放滑动动画
 * @date 创建时间:2020/4/11.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    //最小缩放比例为0.75
    private static final float MIN_SCALE = 0.75f;
    //最小透明度为0.5
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        L.d("page:" + page.getTag() + " position=" + position);

        /**
         * a->b
         * a pos = [0, -1]
         * b pos = [1, 0]
         * b->a
         * a pos = [-1, 0]
         * b pos = [0, 1]
         */
        if (position < -1) {
            //处于最左边
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        } else if (position < 1) {
            if (position < 0) {
                //左边界面a

                // 缩放比例范围 a->b = [1, 0.75f]   b->a = [0.75f, 1]
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);

                //透明度范围 a->b = [1, 0.5f]   b->a = [0.5f, 1]
                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                page.setAlpha(alphaA);
            } else {
                //右边界面b

                //缩放比例范围 a->b = [0.75f, 1]   b->a = [1, 0.75f]
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
                page.setScaleX(scaleB);
                page.setScaleY(scaleB);

                //透明度范围 a->b = [0.5f, 1]   b->a = [1, 0.5f]
                float alphaA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setAlpha(alphaA);
            }
        } else {
            //处于最右边
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        }


    }
}
