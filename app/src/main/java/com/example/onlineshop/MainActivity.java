package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText email;
    EditText password;
    String user;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_check(v);
            }

        });
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void database_check(View view) {
        email=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        if(email.getText().toString().trim().length()==0 || password.getText().toString().trim().length()==0) {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        } else {
            myDatabase database= new myDatabase(MainActivity.this);
            if(database.checkUser(email.getText().toString().trim(), password.getText().toString().trim())) {
                user=email.getText().toString().trim();
                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
                }
            else {
                Toast.makeText(this, "E-mail or password incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

    }
}



