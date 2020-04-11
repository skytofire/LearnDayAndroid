package com.example.learndayandroid.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learndayandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author By skytofire
 * @explain 主界面icon自定义view（图片+文字）
 * @date 创建时间:2020/4/11.
 */
public class TableView extends FrameLayout {

    private TextView mTextView;
    private ImageView mIcon;
    private ImageView mIconSelect;
    private final static int DEFAULT_COLOR = Color.parseColor("#ff000000");
    private final static int DEFAULT_COLOR_SELECT = Color.parseColor("#FF45C01A");


    public TableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.table_view, this);
        mIcon = findViewById(R.id.icon_iv);
        mIconSelect = findViewById(R.id.icon_iv_select);
        mTextView = findViewById(R.id.tv_title);
    }

    public void changeIconAndProgress(int icon, int iconSelect, String title){
        mIcon.setBackgroundResource(icon);
        mIconSelect.setBackgroundResource(iconSelect);
        mTextView.setText(title);
    }

    public void setProgress(float progress){
        mIcon.setAlpha(1 - progress);
        mIconSelect.setAlpha(progress);
        mTextView.setTextColor(evaluate(progress, DEFAULT_COLOR, DEFAULT_COLOR_SELECT));
    }

    /**
     * 根据progress动态调整颜色
     * @param fraction progress
     * @param startValue 默认颜色
     * @param endValue 选中颜色
     * @return
     */
    public int evaluate(float fraction, int startValue, int endValue) {
        int startInt = (Integer) startValue;
        float startA = ((startInt >> 24) & 0xff) / 255.0f;
        float startR = ((startInt >> 16) & 0xff) / 255.0f;
        float startG = ((startInt >>  8) & 0xff) / 255.0f;
        float startB = ( startInt        & 0xff) / 255.0f;

        int endInt = (Integer) endValue;
        float endA = ((endInt >> 24) & 0xff) / 255.0f;
        float endR = ((endInt >> 16) & 0xff) / 255.0f;
        float endG = ((endInt >>  8) & 0xff) / 255.0f;
        float endB = ( endInt        & 0xff) / 255.0f;

        // convert from sRGB to linear
        startR = (float) Math.pow(startR, 2.2);
        startG = (float) Math.pow(startG, 2.2);
        startB = (float) Math.pow(startB, 2.2);

        endR = (float) Math.pow(endR, 2.2);
        endG = (float) Math.pow(endG, 2.2);
        endB = (float) Math.pow(endB, 2.2);

        // compute the interpolated color in linear space
        float a = startA + fraction * (endA - startA);
        float r = startR + fraction * (endR - startR);
        float g = startG + fraction * (endG - startG);
        float b = startB + fraction * (endB - startB);

        // convert back to sRGB in the [0..255] range
        a = a * 255.0f;
        r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
        g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
        b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;

        return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
    }

}
