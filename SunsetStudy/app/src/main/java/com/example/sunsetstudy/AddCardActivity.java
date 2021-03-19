package com.example.sunsetstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity{
    Button mButton;
    EditText mEdit;
    TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
        mButton = (Button)findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEdit   = (EditText)findViewById(R.id.editText1);
                mText = (TextView)findViewById(R.id.textView1);
                mText.setText("Welcome "+mEdit.getText().toString()+"!");
            }
        });
    }
}
