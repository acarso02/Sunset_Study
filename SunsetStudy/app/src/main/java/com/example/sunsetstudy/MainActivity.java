package com.example.sunsetstudy;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
    public static AppCompatActivity activity;
    RVAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton addButton;
        setContentView(R.layout.activity_main);
        //make status bar black
        this.getWindow().setStatusBarColor(Color.BLACK);

        activity = this;
        addButton = (ImageButton) findViewById(R.id.addProject);
        this.loadData();

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buildPopup(findViewById(R.id.main_constraint_layout));
            }
        });
    }

    protected void onStart() {
        super.onStart();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        projectList = dataBaseHelper.getAll();
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        rv = findViewById(R.id.recycler_view);
        myAdapter = new RVAdapter(this, projectList);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(projectList);
        editor.putString("task list", json);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();


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
                //finish before reopening
                finish();
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

    private void buildPopup(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        Button cancelButton;

        builder.setTitle("Project Title");
        builder.setView(editText);
        editText.setSingleLine();
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(editText.getText().toString().matches("")){
                    builder.setMessage("Please enter a valid name");
                }
                else{
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    Project project = new Project(editText.getText().toString().trim());
                    MainActivity.projectList.add(project);
                    dataBaseHelper.addOne(project);
                    onStart();
                }
                }
            });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            saveData();
            //do the things u wanted
        }
    }

    public void openAddCardActivity(){
        Intent i = new Intent(this, AddCardActivity.class);
        startActivity(i);
    }

    public void openActivityQuestions(){
        Intent i = new Intent(this, QuestionsActivity.class);
        startActivity(i);
    }

    public void displayDelete(final int position, final Context ct){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ct);

        builder.setTitle("Delete " + projectList.get(position).Name + "?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(ct, "mock delete!",
                        Toast.LENGTH_LONG).show();
                //remove from database
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteOne(projectList.get(position));

                //remove from list
                myAdapter.removeItem(position);
                myAdapter.notifyDataSetChanged();
                saveData();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do nothing
            }
        });
        builder.show();
    }

    public void setSelected(final int position, final Context ct){

        //set widget
        broadcastActiveProject(position);

        //update database
        projectList.get(position).setIsActive(true);
        broadcastActiveProject(position);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.update(projectList.get(position));
    }

    public static Project getActiveProject(){
        for(Project project : projectList){
            if(project.isActive){
                return project;
            }
        }
        return null;
    }

    public void broadcastActiveProject(int position){
        Gson gson = new Gson();
        Context context = getApplicationContext();
        Intent i = new Intent(context, MyAppWidgetProvider.class);
        i.setAction("UPDATE_ACTIVE_PROJECT");
        i.putExtra("project", gson.toJson(projectList.get(position)));
        context.sendBroadcast(i);
    }

}