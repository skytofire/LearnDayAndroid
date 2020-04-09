package com.example.learndayandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.learndayandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author By skytofire
 * @explain 创建说明
 * @date 创建时间:2020/4/9.
 */
public class TabFragment extends Fragment {

    private TextView mTextViewTitle;
    private String mTitle;
    private static final String BUNDLE_KEY = "key_title";

    public static interface OnTitleClickListener{
        /**
         * 同Activity通信
         * @param title 修改内容
         */
        void onClick(String title);
    }
    private OnTitleClickListener titleClickListener;
    public void setOnTitleClickListener(OnTitleClickListener listener){
        this.titleClickListener = listener;
    }

    public static TabFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, title);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            mTitle = arguments.getString(BUNDLE_KEY, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextViewTitle = view.findViewById(R.id.text_title);
        mTextViewTitle.setText(mTitle);
        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleClickListener != null){
                    titleClickListener.onClick("change");
                }
            }
        });
    }

    public void changeTitle(String title){
        if (!isAdded()){
            return;
        }
        mTextViewTitle.setText(title);
    }
}
