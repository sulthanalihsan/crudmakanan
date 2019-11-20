package com.catatanasad.crudmakanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.catatanasad.crudmakanan.adapter.MakananAdapter;
import com.catatanasad.crudmakanan.model.DataItem;
import com.catatanasad.crudmakanan.model.ResponseMakanan;
import com.catatanasad.crudmakanan.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMakanan = findViewById(R.id.rv_makanan);
        rvMakanan.setHasFixedSize(true);
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));

        getMakanan();
    }

    private void getMakanan(){

        ApiClient apiClient = new ApiClient();
        apiClient.service.getMakanan().enqueue(new Callback<ResponseMakanan>() {
            @Override
            public void onResponse(Call<ResponseMakanan> call, Response<ResponseMakanan> response) {

                if (response.isSuccessful()){

                    ResponseMakanan responseMakanan = response.body(); //data ditangkap
                    List<DataItem> dataItems = responseMakanan.getData();

                    MakananAdapter adapter = new MakananAdapter(dataItems, MainActivity.this);
                    rvMakanan.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ResponseMakanan> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getMakanan();
    }

    // todo panggil layout menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // todo atur action di layout menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_insert){
            startActivity(new Intent(MainActivity.this, InsertActivity.class));
        }

        if (item.getItemId() == R.id.menu_logout){

            // ketika logout hapus data yang ada di sharedpreferences
            Preference.removeDataLogin(MainActivity.this);

            // ketika logout lgsung intent ke LoginActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}