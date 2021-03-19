package com.example.sunsetstudy;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.add_question_button);

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAdd();
            }
        });
    }

    public void openActivityAdd(){
        Intent i = new Intent(this, AddCardActivity.class);
        startActivity(i);
    }





}