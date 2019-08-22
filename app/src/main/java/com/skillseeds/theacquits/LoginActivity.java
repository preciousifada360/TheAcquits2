package com.skillseeds.theacquits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_email_login;
    private EditText editText_password_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email_login = findViewById(R.id.editText_email_login);
        editText_password_login = findViewById(R.id.editText_password_login);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_signup:
                break;

        }
    }

    private void userLogin() {
        String email = editText_email_login.getText().toString().trim();
        String password = editText_email_login.getText().toString().trim();

        if(email.isEmpty()){
            editText_email_login.setError("Email is Required");
            editText_email_login.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_email_login.setError("A valid Email is Required");
            editText_email_login.requestFocus();
            return;
        }


        if(password.isEmpty()){
            editText_password_login.setError("Password is Required");
            editText_password_login.requestFocus();
            return;
        }



        if(password.length()<6){
            editText_password_login.setError("Password should be more than 6 values");
            editText_password_login.requestFocus();
            return;
        }





    }
}
