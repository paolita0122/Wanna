package com.example.wanna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    //private FirebaseAuth.AuthStateListener authStateListener;

    private Button buttonAutos;
    public void filterData(View view){
        Button buttonPressed = (Button) view;
        Log.i("Button pressed", buttonPressed.getTag().toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.settings:
                Log.i("Item Selected", "Settings");
                return true;
            case R.id.logout:
                Log.i("Item Selected", "Help");
                firebaseAuth.getInstance().signOut();
                Intent intentLogout =  new Intent(StartActivity.this, Login.class);
                startActivity(intentLogout);
                finish();


            default: return false;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        buttonAutos= findViewById(R.id._buttonAutos);
        buttonAutos.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, Activity2.class);
                startActivity(intent);
            }


        });





    }
}
