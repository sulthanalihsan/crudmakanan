package com.catatanasad.crudmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catatanasad.crudmakanan.model_login.Data;
import com.catatanasad.crudmakanan.model_login.ResponseLogin;
import com.catatanasad.crudmakanan.network.ApiClient;

import java.util.PriorityQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_login_email);
        edtPassword = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.btn_login);

        String dataPrefEmail = Preference.getDataEmail(LoginActivity.this);
        String dataPrefNama = Preference.getDataName(LoginActivity.this);

        if (!TextUtils.isEmpty(dataPrefEmail) || !TextUtils.isEmpty(dataPrefNama)){

            Toast.makeText(this, "Selamat datang " + dataPrefNama, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "tidak boleh ksong", Toast.LENGTH_SHORT).show();
                }
                else{

                    ApiClient apiClient = new ApiClient();
                    apiClient.service.login(email, password).enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if (response.isSuccessful()){

                                ResponseLogin responseLogin = response.body();
                                String pesan = responseLogin.getPesan();
                                boolean status = responseLogin.isSukses();

                                if (status){

                                    // ambil data nama dan email
                                    Data data = responseLogin.getData();
                                    String email = data.getUserEmail();
                                    String nama = data.getUserNama();

                                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();

                                    // save data nama dan email ke sharedpreference
                                    Preference.saveDataLogin(LoginActivity.this, email, nama);

                                    // intent ke MainActivity
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }



}