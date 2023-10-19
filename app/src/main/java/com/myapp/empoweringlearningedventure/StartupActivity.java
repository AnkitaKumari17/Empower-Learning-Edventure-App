package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class StartupActivity extends AppCompatActivity {

    Button student, teacher;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getSupportActionBar().hide();

        teacher = findViewById(R.id.btnTeacher);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StartupActivity.this, "Teacher Button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        student = findViewById(R.id.btnStudent);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StartupActivity.this, "Student Button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}