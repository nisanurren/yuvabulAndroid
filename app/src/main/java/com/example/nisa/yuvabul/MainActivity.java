package com.example.nisa.yuvabul;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    EditText mail, password;
    Button login;
    TextView signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        signUp = (TextView) findViewById(R.id.link_signup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }

        });



    }

    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void signIn() {
        final User user = new User();
        user.mail=mail.getText().toString();
        user.password=password.getText().toString();

        final Intent intent = new Intent(this, HomePage.class);
        String URL = "http://10.0.2.2:8080/SignInControl/"+user.mail+"/"+user.password;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Rest Responsess ", response.toString());
                        intent.putExtra("mail",user.mail);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Rest Response", error.toString());
                        Toast.makeText(getApplicationContext(), "Kullanıcı Bilgileri Yanlış", Toast.LENGTH_LONG).show();
                    }
                }

        );

        requestQueue.add(objectRequest);




    }




}

