package com.example.workorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText eUser, ePassword;
    private Button btn;


    private String Username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUser= findViewById(R.id.usr);
        ePassword = findViewById(R.id.pass);
        btn = findViewById(R.id.btn_login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(TextUtils.isEmpty(eUser.getText().toString()) || TextUtils.isEmpty((ePassword.getText().toString()))){
               Toast.makeText(LoginActivity.this,"username or password Required", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });

    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(eUser.getText().toString());
        loginRequest.setPassword(ePassword.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userlogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"LOGIN BERHASIL", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("data", loginResponse.getUsername()));
                        }
                    }, 700);
                }else {
                    Toast.makeText(LoginActivity.this,"LOGIN GAGAL!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throw"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}