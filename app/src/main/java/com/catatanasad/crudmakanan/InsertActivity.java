package com.catatanasad.crudmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.catatanasad.crudmakanan.model.ResponseMakanan;
import com.catatanasad.crudmakanan.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    private EditText edtNama, edtHarga, edtUrlGambar;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtHarga = findViewById(R.id.edt_insert_harga);
        edtNama = findViewById(R.id.edt_insert_nama);
        edtUrlGambar = findViewById(R.id.edt_insert_url);
        btnInsert = findViewById(R.id.btn_insert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = edtNama.getText().toString().trim();
                String harga = edtHarga.getText().toString().trim();
                String urlGambar = edtUrlGambar.getText().toString().trim();

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(harga) || TextUtils.isEmpty(urlGambar)){
                    Toast.makeText(InsertActivity.this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    actionInsert(nama, harga, urlGambar);
                }
            }
        });
    }

    private void actionInsert(String nama, String harga, String urlGambar){

        ApiClient apiClient = new ApiClient();
        apiClient.service.insertMakanan(nama, harga, urlGambar).enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                if (response.isSuccessful()){

                    ResponseMakanan responseMakanan = response.body();
                    String pesan = responseMakanan.getPesan();
                    Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}