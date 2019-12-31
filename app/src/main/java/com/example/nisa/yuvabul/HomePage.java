package com.example.nisa.yuvabul;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.spec.PSSParameterSpec;

public class HomePage extends AppCompatActivity {

    ImageButton createPostButton;
    String mail;
    int userId;
    String userMail;
    ListView postsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        postsList=findViewById(R.id.postsList);
        createPostButton=findViewById(R.id.createPostButton);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Gönderiler");


        String URL1 = "http://10.0.2.2:8080/GetAllPosts";
        final RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest=new JsonArrayRequest(
                URL1,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Rest response",response.toString());
                        Gson gson= new Gson();

                        Post[] responseModel=gson.fromJson(response.toString(),Post[].class);



                        printPosts(responseModel);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Response", error.toString());
                    }
                });


        requestQueue1.add(arrayRequest);









        final User tempuser =new User();
        mail=getIntent().getExtras().getString("mail");
        String URL = "http://10.0.2.2:8080/getUser/"+mail;
        final RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest2 = new JsonObjectRequest(
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Rest Responseseget", response.toString());

                        Gson gson=new Gson();

                        User tempuser=gson.fromJson(response.toString(),User.class);
                        userId=tempuser.user_id;
                        userMail=tempuser.mail;





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Rest Response", error.toString());
                    }
                }

        );

        requestQueue2.add(objectRequest2);


        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               openCreatePostActivity();
            }
        });

    }

    public void openCreatePostActivity() {
        Intent intent = new Intent(this, CreatePostActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("userMail",userMail);
        startActivity(intent);
    }

    public void printPosts(Post[] responseModel){

        CustomAdapter adapter =new CustomAdapter(this,responseModel);
        if(postsList != null){
            postsList.setAdapter(adapter);
        }


       }




    }



