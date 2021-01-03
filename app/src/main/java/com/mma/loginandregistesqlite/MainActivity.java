package com.mma.loginandregistesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    EditText e1,e2,e3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        e1 = (EditText)findViewById(R.id.Email);
        e2 = (EditText)findViewById(R.id.Password);
        e3 = (EditText)findViewById(R.id.CPassword);
        b1 = (Button)findViewById(R.id.Register);
        b2 = (Button) findViewById(R.id.LoginButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("")){
                    Toast.makeText(getApplicationContext(),"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if (s2.equals(s3)){

                        Boolean checkemail = db.checkemail(s1);
                        if (checkemail==true){
                            Boolean insert = db.insert(s1,s2);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                else {

                Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}