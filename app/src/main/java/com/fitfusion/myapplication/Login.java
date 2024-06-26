package com.fitfusion.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private Button signUpButton;

    private  EditText email;
    private EditText password;
    private Button loginBtn;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signUpButton = findViewById(R.id.signUpBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.log_in_button);

        auth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if (txt_email.isEmpty() || txt_password.isEmpty()){
                    Toast.makeText(Login.this, "Email or Password is Empty!!!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txt_email, txt_password);
                }

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean validateInput(String email, String password) {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }


    public void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(Login.this, "Login User Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("Number", "1");
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(Login.this, "Login Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        setupUIView();
//    }
//
//    private void setupUIView() {
//
//        signUpButton = findViewById(R.id.signUpBtn);
//        inputEmail = findViewById(R.id.username);
//        //inputUsername.setFilters(new InputFilter[]{new TextInputFilter(20)});
//        inputPassword = findViewById(R.id.password);
//        //inputPassword.setFilters(new InputFilter[]{new TextInputFilter(20)});
//        checkBoxRememberMe = findViewById(R.id.remember_me_chk);
//        loginButton = findViewById(R.id.log_in_button);
//        //Paper.init(this);
//        loadingBar = new ProgressDialog(this);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String txt_email = inputEmail.getText().toString();
//                String txt_password = inputPassword.getText().toString();
//                loginUser(txt_email, txt_password);
//                finish();
//            }
//        });
//
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, RegistryActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    private void loginUser(String email, String password) {
//        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(Login.this, "Login User Successful", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Login.this, Tracking.class));
//                finish();
//            }
//        });
//    }
//
//
//}

//    private void LoginUser(){
//        String name=inputEmail.getText().toString();
//        String password=inputPassword.getText().toString();
//
//        if(TextUtils.isEmpty(name)){
//            Toast.makeText(this, "Please enter your username...", Toast.LENGTH_SHORT).show();
//        }
//        else if(TextUtils.isEmpty(password)){
//            Toast.makeText(this, "Please enter your password...", Toast.LENGTH_SHORT).show();
//        }else {
//            loadingBar.setTitle("Login Account");
//            loadingBar.setMessage("Please wait. We are checking the credentials.");
//            loadingBar.setCanceledOnTouchOutside(false);
//            loadingBar.show();
//
////            CheckCredentials(name,password);
//        }
//    }

//    private void CheckCredentials(String name, String password){
//
//
//    }