package test.retrofit.com.retrofitvia.view;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.retrofit.com.retrofitvia.R;
import test.retrofit.com.retrofitvia.RegisterActivity;
import test.retrofit.com.retrofitvia.classes.Adapter;
import test.retrofit.com.retrofitvia.classes.G;
import test.retrofit.com.retrofitvia.classes.Model;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRegister= findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        getPermission();
         initserver();
      //  getmap();


    }

    private void setupRecyclerView(List<Model> listModel) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(MainActivity.this, listModel);
        recyclerView.setAdapter(adapter);

    }

    //ریکویست برای دریافت کل یوزرها
    private void initserver() {
        G.getApiService().getUsers().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
               // Log.i(TAG, "onResponse: " + response.body().size());
                setupRecyclerView(response.body());

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, " مشکلی پیش آمده است ", Toast.LENGTH_SHORT).show();

            }
        });
    }
// ریکویست برای دریافت یوزرها با علاقمندی رنگ
    public void getmap() {
        Map<String, String> map = new HashMap<>();
        map.put("color_fav", "قرمز");
        map.put("name", "neda");
        G.apiService.getUserFav(map).enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                setupRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });

    }


    private void getPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.INTERNET)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Toast.makeText(MainActivity.this, "on permission granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }
}
