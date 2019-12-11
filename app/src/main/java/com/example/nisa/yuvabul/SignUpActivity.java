package com.example.nisa.yuvabul;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {


    EditText mail, password, name, surname;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();



        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        signUp = (Button) findViewById(R.id.btn_sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();

            }
        });


    }


    public void signUp() {
        User user = new User();
        user.name = name.getText();
        user.mail = mail.getText();
        user.password = password.getText();
        user.surname = surname.getText();



        String URL = "http://localhost:8080/SignUpUser";

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mail",mail);
            jsonBody.put("password",password);
            jsonBody.put("name",name);
            jsonBody.put("surname",surname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                URL,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Rest Responsess ", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Rest Response", error.toString());
                    }
                }

        );

        requestQueue.add(objectRequest);




    }

}