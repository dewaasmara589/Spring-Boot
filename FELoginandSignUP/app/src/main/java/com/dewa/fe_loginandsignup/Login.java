package com.dewa.fe_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private TextInputLayout tfEmailLogin, tfPasswordLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tfEmailLogin = (TextInputLayout) findViewById(R.id.tfEmailLogin);
        tfPasswordLogin = (TextInputLayout) findViewById(R.id.tfPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            if (checkEmailandPassword()){
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkEmailandPassword(){
        String emailLogin = tfEmailLogin.getEditText().getText().toString().trim();
        String passwordLogin = tfPasswordLogin.getEditText().getText().toString().trim();

        if (emailLogin.isEmpty()){
            tfEmailLogin.getEditText().setError("Required");
            tfEmailLogin.requestFocus();
            return false;
        } else if (passwordLogin.isEmpty()) {
            tfPasswordLogin.getEditText().setError("Required");
            tfPasswordLogin.requestFocus();
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()) {
            tfEmailLogin.getEditText().setError("Enter valid Email address !");
            tfEmailLogin.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}