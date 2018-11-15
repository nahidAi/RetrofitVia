package test.retrofit.com.retrofitvia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import test.retrofit.com.retrofitvia.classes.G;
import test.retrofit.com.retrofitvia.classes.Model;
import test.retrofit.com.retrofitvia.view.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
   // private EditText edtPass;
    private EditText edtEmail;
    private EditText image_url;
    private Button register;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edt_name);
       // edtPass = findViewById(R.id.edt_pass);
        edtEmail = findViewById(R.id.edt_email);
        image_url = findViewById(R.id.edt_image_url);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(String.valueOf(edtName.getText()),String.valueOf(edtEmail.getText()),String.valueOf(image_url.getText()));
            }
        });
    }





    private void registerUser(String name, String email,String image_url) {
        G.getApiService().registerUser(name, email,image_url).enqueue(new Callback<Model>() {
            @Override
            public void onResponse(retrofit2.Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                }
            }

            @Override
            public void onFailure(retrofit2.Call<Model> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());

            }
        });

    }
}
