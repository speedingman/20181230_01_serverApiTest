package com.example.zeros.a20181230_01_serverapitest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private android.widget.EditText userIDEdt;
    private android.widget.EditText userPWEdt;
    private android.widget.TextView pasTxt;
    private android.widget.TextView findTxt;
    private android.widget.Button loginBtn;

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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
//                post방식으로 첨부 예시
                RequestBody requestBody = new FormBody.Builder().add("user_id",userIDEdt.getText().toString()).add("password",userPWEdt.getText().toString()).build();

//                서버에 요청을 담당하는 Requset  클래스를 사용

                Request request = new Request.Builder().url("http://api-dev.lebit.kr/auth").post(requestBody).build();

//                client에게 request에 담긴 접속정보를 실행해달라고 요청.

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(mContext, "서버와의 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
////                                Toast.makeText(mContext, "서버와의 연결 성공", Toast.LENGTH_SHORT).show();
////
////                                Log.d("리스폰스==>",response.body().toString());
//
//
//                            }
//                        });
                        String responseBady = response.body().string();
                        Log.d("응답내용",responseBady);
                    }
                });


            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.findTxt = (TextView) findViewById(R.id.findTxt);
        this.pasTxt = (TextView) findViewById(R.id.pasTxt);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.userPWEdt = (EditText) findViewById(R.id.userPWEdt);
        this.userIDEdt = (EditText) findViewById(R.id.userIDEdt);

    }
}
