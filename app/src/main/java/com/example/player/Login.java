package com.example.player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Login extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = Login.this;
    private EditText emaillogin, passwordlogin;
    private Button loginBtn;
    private TextView Donthave;
    private InputChecker inputChecker;
    private DataBase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        emaillogin = findViewById(R.id.edt_emaillogin);
        passwordlogin = findViewById(R.id.edt_passwordlogin);
        loginBtn = findViewById(R.id.btn_login);
        Donthave = findViewById(R.id.donthaveaccount);

    }


    private void initListeners() {
        loginBtn.setOnClickListener(this);
        Donthave.setOnClickListener(this);
    }

    private void initObjects() {
         database = new DataBase(activity);
        inputChecker = new InputChecker(activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                verifyFromSQLite();
                break;
            case R.id.donthaveaccount:
                Intent intentdonthave = new Intent(getApplicationContext(), Registered.class);
                startActivity(intentdonthave);
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputChecker.isInputEditTextEmail(emaillogin, getString(R.string.error_email))) {
            return;
        }
        if (!inputChecker.isInputEditTextPassword(passwordlogin, getString(R.string.error_password))) {
            return;
        }

        if (database.checkUser(emaillogin.getText().toString().trim()
                , passwordlogin.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, Video.class);
            accountsIntent.putExtra("EMAIL", emaillogin.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(this,"Wrong email and password",Toast.LENGTH_SHORT).show();
        }
    }


    private void emptyInputEditText() {
        emaillogin.setText(null);
        passwordlogin.setText(null);
    }
}

