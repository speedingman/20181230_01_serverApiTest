package com.example.zeros.a20181230_01_serverapitest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zeros.a20181230_01_serverapitest.Utills.PasswordUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Sign_upActivity extends BaseActivity {


    private EditText userIdEdt;
    private EditText userPwEdt;
    private EditText userPwCheckEdt;
    private EditText userNameEdt;
    private EditText userEmailEdt;
    private EditText userPhoneEdt;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userIdEdt.getText().equals("")) {
                    Toast.makeText(mContext, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
//                1. 아이디는 반드시 8글자 이상
//                2. 비밀번호도 8글자 이상
//                3. 비밀번호와 다시입력한 비밀번호가 같아야함

                if (userIdEdt.getText().length() < 6) {
                    Toast.makeText(mContext, "아이디는 6글자 이상 이어야 합니다.", Toast.LENGTH_SHORT).show();

                }
                if (userPwEdt.getText().length() < 8) {
                    Toast.makeText(mContext, "비밀번호는 8글자 이상 이어야 합니다.", Toast.LENGTH_SHORT).show();

                }
                if (!userPwEdt.getText().toString().equals(userPwCheckEdt.getText().toString())) {
                    Toast.makeText(mContext, "비밀번호가 서로 다릅니다.", Toast.LENGTH_SHORT).show();
                }

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder().add("user_id",userIdEdt.getText().toString()).add("password",PasswordUtil.getEncrytedPassword(userPwEdt.getText().toString()))
                                             .add("name",userNameEdt.getText().toString()).add("phone",userPhoneEdt.getText().toString()).add("email",userEmailEdt.getText().toString()).build();

                Request request = new Request.Builder().url("http://api-dev.lebit.kr/auth").put(requestBody).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext, "서버통신 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseBody = response.body().string();
                        Log.d("회원가입리스폰스", responseBody);

                        try {
                            JSONObject root = new JSONObject(responseBody);
                            final int code = root.getInt("code");
                            final String message = root.getString("message");

                            Log.d("회원가입리스폰스1","코드 : "+code);
                            Log.d("회원가입리스폰스1","메세지 : "+message);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(code == 200){
                                        Toast.makeText(mContext, "회원가입에 성공 하셨습니다.", Toast.LENGTH_SHORT).show();
                                        finish();

                                    }else{

                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.userPhoneEdt = (EditText) findViewById(R.id.userPhoneEdt);
        this.userEmailEdt = (EditText) findViewById(R.id.userEmailEdt);
        this.userNameEdt = (EditText) findViewById(R.id.userNameEdt);
        this.userPwCheckEdt = (EditText) findViewById(R.id.userPwCheckEdt);
        this.userPwEdt = (EditText) findViewById(R.id.userPwEdt);
        this.userIdEdt = (EditText) findViewById(R.id.userIdEdt);
    }
}
