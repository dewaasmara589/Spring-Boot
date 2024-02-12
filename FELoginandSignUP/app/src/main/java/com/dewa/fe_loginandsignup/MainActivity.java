package com.dewa.fe_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tfFirstName, tfLastName, tfEmail, tfPassword, tfConfirm;
    private TextView tvTextSpanLogin;
    private Button btnSignUp;

    private HashMap<String, String> queryParameter = new LinkedHashMap<>();

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
                CallAPI callAPI = new CallAPI();

                queryParameter.put("firstName", tfFirstName.getEditText().getText().toString().trim());
                queryParameter.put("lastName", tfLastName.getEditText().getText().toString().trim());
                queryParameter.put("email", tfEmail.getEditText().getText().toString().trim());
                queryParameter.put("password", tfPassword.getEditText().getText().toString().trim());

                callAPI.execute("POST");

                Toast.makeText(getApplicationContext(), "Success Sign Up Account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class CallAPI extends AsyncTask<String, String,String> {
        protected String doInBackground(String... method) {
            try{
                String baseURL = "http://192.168.38.209:9080/api/user/register";
                URL urlObj = new URL(baseURL);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod(method[0]);
                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                StringBuilder sbParams = new StringBuilder();
                int i = 0;
                for (String key : queryParameter.keySet()) {
                    try {
                        if (i != 0){
                            sbParams.append("&");
                        }
                        sbParams.append(key).append("=")
                                .append(URLEncoder.encode(queryParameter.get(key), "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                String paramsString = sbParams.toString();

                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
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