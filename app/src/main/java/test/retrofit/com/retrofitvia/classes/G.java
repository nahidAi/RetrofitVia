package test.retrofit.com.retrofitvia.classes;


import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class G extends Application {
    private static Context context;
    private static Retrofit retrofit;
    public static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        apiService = retrofit.create(ApiService.class);


    }

    public static Context getContext() {
        return context;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
