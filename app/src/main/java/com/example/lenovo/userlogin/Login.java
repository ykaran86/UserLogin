package com.example.lenovo.userlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SQLiteDatabaseHandler helper = new SQLiteDatabaseHandler(this);
    EditText _userId,_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //helper.ClearRecords();
    }

    public void login(View v)
    {
        _userId=(EditText)findViewById(R.id.user_ID);
        String user_id=_userId.getText().toString();
        _password=(EditText)findViewById(R.id.pswrd);
        String password=_password.getText().toString();
        String Password1=helper.SearchPass(user_id);
        if(user_id.length()==0||password.length()==0)
        {
            Toast.makeText(this,"Please fill every input field!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Password1.equals("Not Found"))
        {
            Toast.makeText(this,user_id+" is not registered!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Password1.equals(password))
        {
            Toast.makeText(getApplicationContext(),"Login Successfull!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("message",user_id);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Password is not Incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void toSignUp(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
