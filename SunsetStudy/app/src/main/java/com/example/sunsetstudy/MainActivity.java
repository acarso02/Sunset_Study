package com.example.sunsetstudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    private Button addButton;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private LinearLayoutManager llm = new LinearLayoutManager(this);
    public static ArrayList<Project> projectList = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button questionScreen = (Button) findViewById(R.id.question_screen_button);
        Button addButton = (Button) findViewById(R.id.add_question_button);

        questionScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityQuestions();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCardActivity();
            }
        });

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        projectList.add(new Project("Proj one"));
        projectList.add(new Project("Proj two"));
        projectList.add(new Project("Proj three"));
        // toolbar
        setSupportActionBar(toolbar);

        // nav drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void onStart() {
        super.onStart();
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        rv = findViewById(R.id.recycler_view);
        RVAdapter myAdapter = new RVAdapter(this, projectList);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(projectList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        projectList = gson.fromJson(json, type);
        if (projectList == null) {
            projectList = new ArrayList<>();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.allProjects:
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.newProject:
//                Intent createProject = new Intent(MainActivity.this, Project.class);
//                startActivity(createProject);
                break;
            case R.id.addCard:
                Intent createCard = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(createCard);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openAddCardActivity(){
        Intent i = new Intent(this, AddCardActivity.class);
        startActivity(i);
    }

    public void openActivityQuestions(){
        Intent i = new Intent(this, QuestionsActivity.class);
        startActivity(i);
    }
}