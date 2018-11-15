package test.retrofit.com.retrofitvia.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 11/9/2018.
 */

public class Model {
    @SerializedName("id")
    private  int id;
    @SerializedName("name")
    private  String name;
    @SerializedName("email")
    private  String email;
    /*@SerializedName("pass")
    private String pass;*/
    @SerializedName("image_url")
    private  String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
   /* public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }*/
}
