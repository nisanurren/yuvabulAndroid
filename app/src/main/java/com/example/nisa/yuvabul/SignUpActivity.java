package com.example.nisa.yuvabul;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

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
        final User user = new User();
        user.name = name.getText().toString();
        user.mail = mail.getText().toString();
        user.password = password.getText().toString();
        user.surname = surname.getText().toString();



        String URL = "http://10.0.2.2:8080/SignUpUser";

        RequestQueue requestQueue = Volley.newRequestQueue(this);



        StringRequest strRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.e("Rest Responsess ", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("Rest Response", error.toString());
                        Toast.makeText(getApplicationContext(), "Kullanıcı Bilgileri Yanlış", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
        protected Map<String, String> getParams()
        {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", user.name);
            params.put("surname", user.surname);
            params.put("password", user.password);
            params.put("mail", user.mail);



            return params;
        }
        };



        requestQueue.add(strRequest);


    }

}