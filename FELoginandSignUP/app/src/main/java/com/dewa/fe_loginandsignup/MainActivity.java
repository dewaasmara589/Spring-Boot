package com.dewa.fe_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout tfFirstName, tfLastName, tfEmail, tfPassword, tfConfirm;
    TextView tvTextSpanLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfFirstName = (TextInputLayout) findViewById(R.id.tfFirstName);
        tfLastName = (TextInputLayout) findViewById(R.id.tfLastName);
        tfEmail = (TextInputLayout) findViewById(R.id.tfEmail);
        tfPassword = (TextInputLayout) findViewById(R.id.tfPassword);
        tfConfirm = (TextInputLayout) findViewById(R.id.tfConfirm);
        tvTextSpanLogin = (TextView) findViewById(R.id.tvTextSpanLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        tvTextSpanLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(view -> {
            if (checkName() && checkEmail() && checkPassword()){
                Toast.makeText(getApplicationContext(), "Success Sign Up Account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkName(){
        String fName = tfFirstName.getEditText().getText().toString();
        String lName = tfLastName.getEditText().getText().toString();

        if (fName.trim().isEmpty()){
            tfFirstName.getEditText().setError("Required");
            tfFirstName.requestFocus();
            return false;
        } else if (lName.trim().isEmpty()) {
            tfLastName.getEditText().setError("Required");
            tfLastName.requestFocus();
            return false;
        }

        return true;
    }

    private boolean checkEmail(){
        String email = tfEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()){
            tfEmail.getEditText().setError("Required");
            tfEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tfEmail.getEditText().setError("Enter valid Email address !");
            tfEmail.requestFocus();
            return false;
        }

        return true;
    }

    private boolean checkPassword(){
        String password = tfPassword.getEditText().getText().toString();
        String confirmPassword = tfConfirm.getEditText().getText().toString();

        if (password.trim().isEmpty()){
            tfPassword.getEditText().setError("Required");
            tfPassword.requestFocus();
            return false;
        } else if (confirmPassword.trim().isEmpty()) {
            tfConfirm.getEditText().setError("Required");
            tfConfirm.requestFocus();
            return false;
        } else if (!password.equals(confirmPassword)) {
            tfPassword.getEditText().setError("Password Not Matching");
            tfConfirm.getEditText().setError("Password Not Matching");
            return false;
        }

        return true;
    }
}