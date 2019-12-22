package com.example.nisa.yuvabul;

import android.text.Editable;
import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nisa on 15-Nov-19.
 */

public class User {

    @SerializedName("mail")
    String  mail;

    @SerializedName("name")
    String name;

    @SerializedName("surname")
    String surname;

    @SerializedName("password")
    String password;

    @SerializedName("user_id")
    int user_id;


}
