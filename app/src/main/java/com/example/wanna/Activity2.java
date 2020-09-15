package com.example.wanna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    public static ArrayList<Item> items;
    public static void construyeItems(){
        items = new ArrayList<>();
        items.add(new Item("blanquito",R.drawable.blanco,16,11));
        items.add(new Item("rojito",R.drawable.rojo,13,6));
        items.add(new Item("negrito",R.drawable.negro,1,21));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        construyeItems();
        ItemAdapter adapter = new ItemAdapter(getApplicationContext(),items);
        recyclerView = findViewById(R.id.mainList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}