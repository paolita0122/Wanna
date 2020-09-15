package com.example.wanna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button loginButton;
    private Button createAcctButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private EditText emailText;
    private EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.email_sign_in_button);
        createAcctButton = findViewById(R.id.email_create_button);
        emailText = findViewById(R.id.email);
        passwordText= findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(emailText.getText().toString())
                        && !TextUtils.isEmpty(passwordText.getText().toString())
                   ){
                    String email = emailText.getText().toString().trim();
                    String password = passwordText.getText().toString().trim();
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                                Intent intentstart = new Intent(Login.this, StartActivity.class);

                                startActivity(intentstart);

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }
        });

        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,CreateAccount.class));
            }
        });



    }
}