package com.example.sunsetstudy;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    private Button addButton;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//        addButton = (Button) findViewById(R.id.add_question_button);

/*        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{ContextCompat.getColor(this, R.color.gradient1),
                        ContextCompat.getColor(this, R.color.gradient2),
                        ContextCompat.getColor(this, R.color.gradient3),
                        ContextCompat.getColor(this, R.color.gradient4),
                        ContextCompat.getColor(this, R.color.gradient5),
                        ContextCompat.getColor(this, R.color.gradient6),
                        ContextCompat.getColor(this, R.color.gradient7),
                        ContextCompat.getColor(this, R.color.gradient9)
                }
        );
        findViewById(R.id.main_constraint_layout).setBackground(gradientDrawable);

 */
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivityAdd();
//            }
//        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.allProjects:
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                break;
//            case R.id.newProject:
//                Intent createProject = new Intent(MainActivity.this, Project.class);
//                startActivity(createProject);
//                break;
            case R.id.addCard:
                Intent createCard = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(createCard);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void openActivityAdd(){
//        Intent i = new Intent(this, AddCardActivity.class);
//        startActivity(i);
//    }
}