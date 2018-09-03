package com.example.lenovo.userlogin;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE=1;
    ImageView uploadImage;
    Button btnUploadPic;
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
        uploadImage=(ImageView)findViewById(R.id.profilepic);
        btnUploadPic=(Button)findViewById(R.id.uploadpic);
        btnUploadPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri selectedImage=data.getData();
            uploadImage.setImageURI(selectedImage);

        }
    }

    public void logout(View v)
    {
        Intent intent=new Intent(this,Login.class);
        Toast.makeText(getApplicationContext(),"Logout Successfull!",Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}
