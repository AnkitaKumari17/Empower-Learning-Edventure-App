package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
//import com.google.firebase.auth.FirebaseAuth;

//import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

   // FirebaseAuth auth;
  //  FirebaseUser user;
    LinearLayout mathQuiz, mathPuzzle, picPuzzle, match, quiz, painting, story, video, notes ;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

    /*    auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user==null){
            Intent intent= new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        }*/
        mathQuiz = findViewById(R.id.btnmathQuiz);
        drawerLayout = findViewById(R.id.drawer_layer);

        mathQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "MathQuiz button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, MathQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quiz = findViewById(R.id.btnQuiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Quiz button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mathPuzzle = findViewById(R.id.btnPuzzle);
        mathPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Puzzle button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, PuzzleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        picPuzzle = findViewById(R.id.btnPicPuzzle);
        picPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Puzzle button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, PicPuzzleActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    private void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void dashboard(View view){
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

}