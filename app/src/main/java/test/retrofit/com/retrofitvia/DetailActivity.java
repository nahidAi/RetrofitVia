package test.retrofit.com.retrofitvia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.retrofit.com.retrofitvia.classes.G;
import test.retrofit.com.retrofitvia.classes.Model;

public class DetailActivity extends AppCompatActivity {
    private int id;
    private static final String TAG = "DetailActivity";
    private Model myList;
    private ImageView imageView;
    private TextView name;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.details_imageView);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        initdata();
    }

    private void initdata() {
       G.apiService.getItemDetails(id).enqueue(new Callback<Model>() {
           @Override
           public void onResponse(Call<Model> call, Response<Model> response) {
               myList = response.body();
               Picasso.get().load(myList.getImage_url()).into(imageView);
               name.setText(myList.getName());
               email.setText(myList.getEmail());

           }

           @Override
           public void onFailure(Call<Model> call, Throwable t) {

           }
       });
    }
}
