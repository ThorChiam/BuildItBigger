package com.shentuo.mylibrary;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shentuo.mylibrary.databinding.ActivityDisplayBinding;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDisplayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_display);
        String s = getIntent().getStringExtra("MY_JOKES");
        binding.jokeContents.setText(s);
    }
}
