package com.example.learndayandroid.transformer;

import android.view.View;

import com.example.learndayandroid.utils.L;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author By skytofire
 * @explain viewpager的旋转滑动动画
 * @date 创建时间:2020/4/11.
 */
public class ScrollTransformer implements ViewPager.PageTransformer {
    //最大旋转角度为15
    private static final float MAX_SCROLL = 15;

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
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight());
            //旋转角度从0 -> -15度
            page.setRotation(MAX_SCROLL * -1);

        } else if (position < 1) {
            if (position < 0) {
                //左边界面a

                // 滑动旋转 旋转中心a->b Y=getHeight() X=[getWidth()*0.5f, getWidth()]
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(page.getHeight());
                //旋转角度从-15度
                page.setRotation(MAX_SCROLL * position);

            } else {
                //右边界面b

                // 滑动旋转 旋转中心b->a Y=getHeight() X=[0, getWidth()*0.5f]
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(page.getHeight());
                //旋转角度从15度 -> 0
                page.setRotation(MAX_SCROLL * position);
            }
        } else {
            //处于最右边
            page.setPivotX(0);
            page.setPivotY(page.getHeight());
            //旋转角度从15度
            page.setRotation(MAX_SCROLL);
        }


    }
}
