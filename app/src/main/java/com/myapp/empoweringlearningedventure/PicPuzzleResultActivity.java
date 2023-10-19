package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PicPuzzleResultActivity extends AppCompatActivity {

    Button btnClose, btnRestart, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picpuzzle_result);

        btnClose = findViewById(R.id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PicPuzzleResultActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PicPuzzleResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext = findViewById(R.id.next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PicPuzzleResultActivity.this, "Level Up", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PicPuzzleResultActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRestart = findViewById(R.id.reset);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PicPuzzleResultActivity.this, "Restart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PicPuzzleResultActivity.this, PicPuzzleActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}