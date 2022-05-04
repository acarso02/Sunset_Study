package com.example.sunsetstudy;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayoutManager llm = new LinearLayoutManager(this);
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    RecyclerView rv;
    String projectName;
    int position;
    RVCardAdapter myAdapter;

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_wrapper);
        final ImageButton addButton;
        activity = this;
        addButton = (ImageButton) findViewById(R.id.addCard);
        //make status bar black
        this.getWindow().setStatusBarColor(Color.BLACK);

        Bundle extras = getIntent().getExtras();
        position = (int) extras.get("position");
        projectName = MainActivity.projectList.get(position).Name;
        this.setTitle(projectName + " - Questions");     //get project name and set it to the title

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        // toolbar
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createCard = new Intent(QuestionsActivity.this, AddCardActivity.class);
                createCard.putExtra("Position", position);
                startActivity(createCard);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        saveCall();
        rv = findViewById(R.id.recycler_view);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            position = 0;  //something is wrong
        } else {
            position = (int) extras.get("position");
        }
        myAdapter = new RVCardAdapter(this, MainActivity.projectList.get(position));
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.allProjects:
                Intent home = new Intent(QuestionsActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.newProject:
//                Intent createProject = new Intent(MainActivity.this, Project.class);
//                startActivity(createProject);
                break;
            case R.id.addCard:
                Intent createCard = new Intent(QuestionsActivity.this, AddCardActivity.class);
                startActivity(createCard);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayDelete(final int cardPosition, final Context ct){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ct);

        builder.setTitle("Delete question?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(ct, "Deleted!",
                        Toast.LENGTH_LONG).show();
                //update projects list
                myAdapter.removeItem(cardPosition);
                myAdapter.notifyDataSetChanged();
                MainActivity.projectList.get(position).getCardList().remove(cardPosition);
                //update database
                DataBaseHelper dataBaseHelper = new DataBaseHelper(QuestionsActivity.this);
                dataBaseHelper.update(MainActivity.projectList.get(position));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do nothing
            }
        });
        builder.show();
    }

    private void saveCall(){
        String message="hello ";
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",message);
        setResult(2,intent);
    }
}