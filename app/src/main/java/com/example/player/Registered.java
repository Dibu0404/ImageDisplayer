package com.example.player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registered extends AppCompatActivity implements View.OnClickListener {
    private EditText nameregistred;
    private EditText Emailregisterd;
    private EditText Passwordregisterd;
    private Button Registerd;
    private TextView Alreadyhave;
    private final AppCompatActivity activity = Registered.this;
    private InputChecker inputChecker;
    private DataBase database;
    private AppUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        nameregistred = findViewById(R.id.edt_nameregistered);
        Emailregisterd = findViewById(R.id.edt_emailregistered);
        Passwordregisterd = findViewById(R.id.edt_passwordregistered);
        Registerd = findViewById(R.id.btn_registerd);
        Alreadyhave = findViewById(R.id.AlreadyHaveAccount);
    }

    private void initListeners() {
        Registerd.setOnClickListener(this);
        Alreadyhave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registered.this,Login.class);
                startActivity(intent);
            }
        });

    }

    private void initObjects(){
        inputChecker = new InputChecker(activity);
        database = new DataBase(activity);
        user = new AppUser();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_registerd:
                postDataToSQLite();
                Intent intent = new Intent(Registered.this,Login.class);
                startActivity(intent);
                break;

            case R.id.AlreadyHaveAccount:
                Intent Alreadyintent = new Intent(Registered.this,Login.class);
                finish();
                break;
        }
    }


    private void postDataToSQLite() {
        if (!inputChecker.isInputEditTextName(nameregistred, getString(R.string.error_name))) {
            return;
        }
        if (!inputChecker.isInputEditTextEmail(Emailregisterd, getString(R.string.error_email))) {
            return;
        }

        if (!inputChecker.isInputEditTextPassword(Passwordregisterd, getString(R.string.error_password))) {
            return;
        }


        if (!database.checkUser(Emailregisterd.getText().toString().trim())) {

            user.setName(nameregistred.getText().toString().trim());
            user.setEmail(Emailregisterd.getText().toString().trim());
            user.setPassword(Passwordregisterd.getText().toString().trim());

            database.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(this,"Successfully",Toast.LENGTH_SHORT).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(this,"Already Exists", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        nameregistred.setText(null);
        Emailregisterd.setText(null);
        Passwordregisterd.setText(null);
    }



}