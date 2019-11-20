package com.catatanasad.crudmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catatanasad.crudmakanan.model.DataItem;
import com.catatanasad.crudmakanan.model.ResponseMakanan;
import com.catatanasad.crudmakanan.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText edtNama, edtHarga, edtUrlGambar;
    private Button btnUpdate, btnDelete;

    public static String KEY_DATA = "key_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        edtNama = findViewById(R.id.edt_update_nama);
        edtHarga = findViewById(R.id.edt_update_harga);
        edtUrlGambar = findViewById(R.id.edt_update_url);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);

        final DataItem dataItem = getIntent().getParcelableExtra(KEY_DATA);
        edtNama.setText(dataItem.getMenuNama());
        edtHarga.setText(dataItem.getMenuHarga());
        edtUrlGambar.setText(dataItem.getMenuGambar());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionDelete(dataItem.getMenuId());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = edtNama.getText().toString().trim();
                String harga = edtHarga.getText().toString().trim();
                String urlgambar = edtUrlGambar.getText().toString().trim();
                String id = dataItem.getMenuId();

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(harga) || TextUtils.isEmpty(urlgambar) || TextUtils.isEmpty(id)){
                    Toast.makeText(UpdateDeleteActivity.this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    actionUpdate(id, nama, harga, urlgambar);
                }
            }
        });
    }

    private void actionUpdate(String id, String nama, String harga, String url){

        ApiClient apiClient = new ApiClient();
        apiClient.service.updateMakanan(nama, harga, url, id).enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                if (response.isSuccessful()){

                    ResponseMakanan responseMakanan = response.body();
                    String pesan = responseMakanan.getPesan();
                    boolean status = responseMakanan.isSukses();

                    if (status){
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void actionDelete(String id){

        ApiClient client = new ApiClient();
        client.service.deleteMakanan(id).enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {
                if (response.isSuccessful()){

                    ResponseMakanan responseMakanan = response.body();
                    String pesan = responseMakanan.getPesan();
                    boolean status = responseMakanan.isSukses();

                    if (status){
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {

            }
        });
    }

}
