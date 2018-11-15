package test.retrofit.com.retrofitvia.classes;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //دریافت یوزرها
    @GET("allusers.php")
    Call<List<Model>> getUsers();

    //دریافت جزییات یوزر
    @GET("userdetail.php")
    Call<Model> getItemDetails(@Query("id") int id);

    //دریافت یوزر بر حسب رنگ مورد علاقه
    @GET("userfav.php")
    Call<List<Model>>getUserFav(@QueryMap Map<String,String>values);

    //رجیستر یوزر
    @POST("register.php")
    @FormUrlEncoded
    Call<Model>registerUser(@Field("name") String name
            ,@Field("email")  String email,@Field("image_url")String image_url);

    //آپلود فایل
    @Multipart
    @POST("upload")
    Call<UploadResponse>upload(@Part MultipartBody.Part body);

}
