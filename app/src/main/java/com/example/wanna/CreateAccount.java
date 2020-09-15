package com.example.wanna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;


    //FireStore connection
    private FirebaseFirestore db =  FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("Users");
    //get widgets

    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressBar progressBar;
    private EditText usernameEditText;
    private Button createAcctButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firebaseAuth = FirebaseAuth.getInstance();

        createAcctButton = findViewById(R.id.email_create_button);
        progressBar = findViewById(R.id.create_account_progress);
        emailEditText = findViewById(R.id.email_account);
        passwordEditText = findViewById(R.id.password_account);
        usernameEditText = findViewById((R.id.username_account));

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if(currentUser!= null){
                    //user is already logged in
                }
                else{
                    //no user yet

                }
            }
        };
        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(emailEditText.getText().toString())
                        && !TextUtils.isEmpty(passwordEditText.getText().toString())
                        && !TextUtils.isEmpty(usernameEditText.getText().toString())){

                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    String username = usernameEditText.getText().toString().trim();
                    createUserEmailAccount(email,password,username);
                }
                else{
                    Toast.makeText(CreateAccount.this,"Empty Fields not allowed",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void createUserEmailAccount(String email, String password, final String username){
        if(!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(username)){
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //we take user to the  page
                                Log.d("TAG", "createUserWithEmail:success");
                                currentUser = firebaseAuth.getCurrentUser();
                                assert currentUser != null;
                                final String currentUserID = currentUser.getUid();

                                //create a user Map to create a user in the user collection
                                HashMap<String, String> userObj = new HashMap<>();
                                userObj.put("userId",currentUserID);
                                userObj.put("username", username);

                                //save to our fire store database
                                collectionReference.add(userObj)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                documentReference.get()
                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                if (task.getResult().exists()) {
                                                                    Toast.makeText(CreateAccount.this, "Loading...", Toast.LENGTH_SHORT).show();

                                                                    progressBar.setVisibility(View.INVISIBLE);
                                                                    String name = task.getResult().getString("username");
                                                                    //Intent intent = new Intent(CreateAccount.this,MainActivity.class);
                                                                    Intent intent = new Intent(CreateAccount.this, StartActivity.class);

                                                                    intent.putExtra("username", name);
                                                                    intent.putExtra("userId",currentUserID);
                                                                   // Log.i("intent","We are here");
                                                                    startActivity(intent);
                                                                   // Log.i("intent2","We are now here");
                                                                }
                                                                else{
                                                                    progressBar.setVisibility(View.INVISIBLE);
                                                                    Toast.makeText(CreateAccount.this, "Not Loading...", Toast.LENGTH_SHORT).show();

                                                                    // Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                                                   // Log.e("OnComplete:Failed","onComplete: Failed=" + task.getException().getMessage());

                                                                }


                                                            }
                                                        });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });



                            }
                            else{
                                //something went wrong
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(CreateAccount.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
        else {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}