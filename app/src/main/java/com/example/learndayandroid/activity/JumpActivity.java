package com.example.learndayandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learndayandroid.MainActivity;
import com.example.learndayandroid.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author By skytofire
 * @explain 创建说明
 * @date 创建时间:2020/4/11.
 */
public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
    }


    public void onGoMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onGoSplashActivity(View view) {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }
}
