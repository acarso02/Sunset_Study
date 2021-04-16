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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

import java.util.Random;

public class AddCardActivity extends AppCompatActivity implements OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{
    private Button subButton;
    private EditText mEditQuestion, mEditAnswer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private String[] projectStrings;
    private String[] hints = {"What is the meaning of life", "How far away is the sun?", "What is Java?", "A group of Owls is called?", "How tall is tall?", "What comes after 3?", "What does a fox say?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_activity);
        mEditQuestion = (EditText)findViewById(R.id.questionBox);
        mEditAnswer = (EditText)findViewById(R.id.answerBox);
        projectStrings = new String[MainActivity.projectList.size()];
        int i = 0;
        Random rand = new Random();

        this.setTitle("Add a Card");
        //random question hint
        mEditQuestion.setHint(hints[rand.nextInt(7)]);

        //populate spinner with project names
        for(Project proj : MainActivity.projectList){
            projectStrings[i] = proj.Name;
            i++;
        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        subButton = (Button)findViewById(R.id.submitButton);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, projectStrings);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String question, answer;
                int selectedItem;
                question = mEditQuestion.getText().toString();
                answer = mEditAnswer.getText().toString();
                if(question.matches("")){
                    //alert field is blank
                }
                else{
                    //add card to the selected project
                   selectedItem =  spinner.getSelectedItemPosition();
                    MainActivity.projectList.get(selectedItem).addCard(question, answer);
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
                //finish before reopening
                finish();
                startActivity(createCard);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

