package com.example.visitorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Add extends AppCompatActivity {
    Button b5;
    EditText e1,e2,e3,e4;
    String getname,getlast,getpurpose,getmeet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        b5=(Button)findViewById(R.id.sub);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.lname);
        e3=(EditText)findViewById(R.id.purpose);
        e4=(EditText)findViewById(R.id.meet);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getname=e1.getText().toString();
                getlast=e2.getText().toString();
                getpurpose= e3.getText().toString();
                getmeet=e4.getText().toString();

                Intent as=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(as);
                if(getname.isEmpty()||getlast.isEmpty()||getpurpose.isEmpty()||getmeet.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"all fields are mandatory",Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        callapi();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            private void callapi() throws JSONException {
                String apiurl="https://log-app-demo-api.onrender.com/addvisitor";
                JSONObject data=new JSONObject();
                data.put("firstname",getname);
                data.put("lastname",getlast);
                data.put("purpose",getpurpose);
                data.put("whomToMeet",getmeet);

                JsonObjectRequest request=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        data,
                        response -> Toast.makeText(getApplicationContext(),"sucesfully added",Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_LONG).show()

                        );
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                        queue.add(request);

            }

        });

    }
}