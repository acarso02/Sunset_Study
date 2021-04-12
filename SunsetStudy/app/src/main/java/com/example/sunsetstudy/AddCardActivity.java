package com.example.sunsetstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class AddCardActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{
    private Button subButton;
    private EditText mEditQuestion, mEditAnswer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_activity);
        subButton = (Button)findViewById(R.id.submitButton);

        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String question, answer;

                mEditQuestion = (EditText)findViewById(R.id.questionBox);
                mEditAnswer = (EditText)findViewById(R.id.answerBox);
                question = mEditQuestion.getText().toString();
                answer = mEditAnswer.getText().toString();

                if(question.matches("")){
                    //alert field is blank
                }
                else{
                    //add card to the selected project
                    MainActivity.projectList.get(0).addCard(question, answer);
                    Toast.makeText(getApplicationContext(), "Submitted!",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        // toolbar
        setSupportActionBar(toolbar);

        // nav drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.allProjects:
                Intent home = new Intent(AddCardActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.newProject:
//                Intent createProject = new Intent(MainActivity.this, Project.class);
//                startActivity(createProject);
                break;
            case R.id.addCard:
                Intent createCard = new Intent(AddCardActivity.this, AddCardActivity.class);
                startActivity(createCard);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

