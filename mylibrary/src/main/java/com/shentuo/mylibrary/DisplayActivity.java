package com.shentuo.mylibrary;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shentuo.mylibrary.databinding.ActivityDisplayBinding;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDisplayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_display);
        String s = getString(R.string.error_message);
        if (getIntent().hasExtra(Constants.MY_JOKE_KEY)){
            s = getIntent().getStringExtra(Constants.MY_JOKE_KEY);
        }
        binding.jokeContents.setText(s);
    }
}
