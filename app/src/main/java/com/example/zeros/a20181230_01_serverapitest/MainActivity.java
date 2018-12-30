package com.example.zeros.a20181230_01_serverapitest;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private android.widget.TextView userNameTxt;
    private android.widget.TextView userIdTxt;
    private android.widget.TextView userEamilTxt;
    private android.widget.TextView userPhoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        String loginId = getIntent().getStringExtra("로그인아이디");
        String loginEmail = getIntent().getStringExtra("이메일");
        String loginName = getIntent().getStringExtra("이름");
        String loginPhone = getIntent().getStringExtra("폰번");


        userNameTxt.setText(loginName);
        userIdTxt.setText(loginId);
        userEamilTxt.setText(loginEmail);
        userPhoneTxt.setText(loginPhone);




    }

    @Override
    public void bindViews() {

        this.userPhoneTxt = (TextView) findViewById(R.id.userPhoneTxt);
        this.userEamilTxt = (TextView) findViewById(R.id.userEamilTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);

    }
}
