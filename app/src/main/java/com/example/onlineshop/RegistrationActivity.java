package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText conf_password;
    EditText name;
    EditText address;
    EditText city;
    EditText phone;
    Button button;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        button = (Button) findViewById(R.id.register_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void input_database(View view) {

        name=(EditText)findViewById(R.id.register_name);
        email=(EditText)findViewById(R.id.register_username);
        address=(EditText)findViewById(R.id.register_address);
        phone=(EditText)findViewById(R.id.register_phone);
        city=(EditText)findViewById(R.id.register_city);
        password=(EditText)findViewById(R.id.register_password);
        conf_password=(EditText)findViewById(R.id.register_password_confirm);

        if(name.getText().toString().trim().length()==0 || email.getText().toString().trim().length()==0 || address.getText().toString().trim().length()==0 || phone.getText().toString().trim().length()==0 || city.getText().toString().trim().length()==0|| password.getText().toString().trim().length()==0|| conf_password.getText().toString().trim().length()==0) {
            Toast.makeText(this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
        }

        else {
        myDatabase database = new myDatabase(RegistrationActivity.this);
        database.insertUser(name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim(), city.getText().toString().trim(), address.getText().toString().trim(), phone.getText().toString().trim());

        Toast.makeText(this, "You have registered!", Toast.LENGTH_SHORT).show();

        user=email.getText().toString().trim();
        Intent intent = new Intent(this, MenuActivity.class);

        }
        }
        //----------------------------------------------------------------------//





       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/
    }
