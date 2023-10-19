package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MathQuizResultActivity extends AppCompatActivity {

    TextView textResult;
    Button btnclose, btnRestart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathquiz_result);

        getSupportActionBar().setTitle("Math Quiz");

        Objects.requireNonNull(getSupportActionBar()).hide();

        textResult = findViewById(R.id.txtResult);
        textResult.setText(" You answered "+ getIntent().getIntExtra("RA",0)+" / 10 ");

        btnclose = findViewById(R.id.close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MathQuizResultActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MathQuizResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRestart = findViewById(R.id.reset);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MathQuizResultActivity.this, "Restart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MathQuizResultActivity.this, MathQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}