package com.example.lenovo.userlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        String User=intent.getStringExtra("message");
        tv=(TextView)findViewById(R.id.welcome);
        tv.setText("Welcome "+User+"!");
        Toast.makeText(this,"Welcome "+User+"!",Toast.LENGTH_SHORT).show();
    }

    public void logout(View v)
    {
        Intent intent=new Intent(this,Login.class);
        Toast.makeText(getApplicationContext(),"Logout Successfull!",Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}
