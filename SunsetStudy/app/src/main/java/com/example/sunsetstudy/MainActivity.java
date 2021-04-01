package com.example.sunsetstudy;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
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
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
//    public void openActivityAdd(){
//        Intent i = new Intent(this, AddCardActivity.class);
//        startActivity(i);
//    }
}