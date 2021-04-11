package com.example.sunsetstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    private LinearLayoutManager llm = new LinearLayoutManager(this);

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        rv = findViewById(R.id.recycler_view);
        RVCardAdapter myAdapter = new RVCardAdapter(this, MainActivity.projectList.get(0));
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = (Button) findViewById(R.id.add_question_button);
    }
}