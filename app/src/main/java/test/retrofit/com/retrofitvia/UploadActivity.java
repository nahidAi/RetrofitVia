package test.retrofit.com.retrofitvia;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.retrofit.com.retrofitvia.classes.G;
import test.retrofit.com.retrofitvia.classes.UploadResponse;

public class UploadActivity extends AppCompatActivity {
    private Button btn_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        btn_upload = findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // برای اینکه برنامه رو بفرستیم جایی که یک فایل رو برامون اتخاب کنه این اینتنت را مینویسیم
                //به محض اینکه دکمه زده شد باید بره به استوریج و فایل رو برداره
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // برای اینکه تایپ فایل رو مشخص کنیم و محدودیتی ایجاد کنیم که از چه نوعی باشه
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    String type = getFileExtension(data.getData());
                    sendUploadRequest(getBytes(inputStream),type);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // این متد قراره کار ارسال ریکویست رو انجام بده برامون
    private void sendUploadRequest(byte[] bytes, String type) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bytes);
        MultipartBody.Part file = MultipartBody.Part.createFormData("file", "my image. " + type, requestFile);
        G.getApiService().upload(file).enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {

            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {

            }
        });

    }
    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}
