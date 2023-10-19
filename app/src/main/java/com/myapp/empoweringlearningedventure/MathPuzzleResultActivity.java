package com.myapp.empoweringlearningedventure;

import static com.myapp.empoweringlearningedventure.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MathPuzzleResultActivity extends AppCompatActivity {

    Button btnClose, btnRestart, btnNext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_math_puzzle_result);

        btnClose = findViewById(id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MathPuzzleResultActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MathPuzzleResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext = findViewById(id.next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MathPuzzleResultActivity.this, "Level Up", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MathPuzzleResultActivity.this, MathPuzzleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRestart = findViewById(id.reset);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MathPuzzleResultActivity.this, "Restart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MathPuzzleResultActivity.this, PuzzleActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}