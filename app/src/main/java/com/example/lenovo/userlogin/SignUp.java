package com.example.lenovo.userlogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _submit;
    EditText _firstName,_lastName,_user_id,_password,_email,_confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toast.makeText(this, "Sign Up!", Toast.LENGTH_SHORT).show();
        openHelper=new SQLiteDatabaseHandler(this);
        _submit=(Button)findViewById(R.id.submit);
        _firstName=(EditText)findViewById(R.id.firstName);
        _lastName=(EditText)findViewById(R.id.lastName);
        _user_id=(EditText)findViewById(R.id.user_id);
        _password=(EditText)findViewById(R.id.password);
        _email=(EditText)findViewById(R.id.email);
        _confirmPassword=(EditText)findViewById(R.id.confirmPassword);
        _submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db=openHelper.getWritableDatabase();
                String fname=_firstName.getText().toString();
                String lname=_lastName.getText().toString();
                String userId=_user_id.getText().toString();
                String pswd=_password.getText().toString();
                String eMail=_email.getText().toString();
                String confirmPswd=_confirmPassword.getText().toString();
                if(fname.length()==0||lname.length()==0||userId.length()==0||pswd.length()==0||eMail.length()==0||confirmPswd.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill every input field",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pswd.equals(confirmPswd))
                {
                    insertData(fname, lname, userId, pswd, eMail);
                    Toast.makeText(getApplicationContext(), "register successfull", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void insertData(String fname,String lname,String userId,String pswd,String eMail){
        ContentValues contentValues= new ContentValues();
        contentValues.put(SQLiteDatabaseHandler.COL_2,fname);
        contentValues.put(SQLiteDatabaseHandler.COL_3,lname);
        contentValues.put(SQLiteDatabaseHandler.COL_4,userId);
        contentValues.put(SQLiteDatabaseHandler.COL_5,pswd);
        contentValues.put(SQLiteDatabaseHandler.COL_6,eMail);
        contentValues.put(SQLiteDatabaseHandler.COL_7,"");
        long id = db.insert(SQLiteDatabaseHandler.TABLE_NAME,null,contentValues);
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("message",userId);
        startActivity(intent);
    }

    public void toLogin(View view)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }
}
