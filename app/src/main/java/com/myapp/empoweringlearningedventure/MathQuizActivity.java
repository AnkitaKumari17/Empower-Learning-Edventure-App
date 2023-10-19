package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MathQuizActivity extends AppCompatActivity {

    TextView textlevel, textRightAnswered, textQuestion;
    Button buttonOpt1,buttonOpt2,buttonOpt3;

    int level = 0;
    int great = 0;
    int rightAnswer = 0;
    String realOperation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathquiz);

        getSupportActionBar().hide();

        textlevel = findViewById(R.id.txtquestions);
        textRightAnswered = findViewById(R.id.txtrightAnswered);
        textQuestion = findViewById(R.id.txtAllquestions);

        buttonOpt1 = findViewById(R.id.btnOption1);
        buttonOpt2 = findViewById(R.id.btnOption2);
        buttonOpt3 = findViewById(R.id.btnOption3);

        textlevel.setText("Q : "+level+" / 10");
        textRightAnswered.setText("RA : "+great+" / 10");

        if(level < 10)
        {
            getARandomQuestion();
        }
    }

    private void getARandomQuestion() {
        buttonOpt1.setBackgroundResource(R.drawable.btn_gradiant_blue);
        buttonOpt2.setBackgroundResource(R.drawable.btn_gradiant_blue);
        buttonOpt3.setBackgroundResource(R.drawable.btn_gradiant_blue);

        int firstNumber = new Random().nextInt(10);
        int secondNumber = new Random().nextInt(10);

        int operator = new Random().nextInt(3) + 1;

        int optionA = new Random().nextInt(100);
        int optionB = new Random().nextInt(100);

        if(operator == 1)
        {
            realOperation = "+";
            rightAnswer = firstNumber + secondNumber;
            textQuestion.setText(firstNumber+ " "+realOperation+ " "+secondNumber +" = ? ");
        }
        else{
            if(operator == 2)
            {
                realOperation = "-";
                if(firstNumber < secondNumber)
                {
                    rightAnswer = secondNumber - firstNumber;
                    textQuestion.setText(firstNumber+ " "+realOperation+ " "+secondNumber +" = ? ");
                }else{
                    rightAnswer = firstNumber - secondNumber;
                    textQuestion.setText(firstNumber+ " "+realOperation+ " "+secondNumber +" = ? ");
                }
            }
            else{
                if(operator == 3)
                {
                    realOperation = "*";
                    rightAnswer = firstNumber * secondNumber;
                    textQuestion.setText(firstNumber+ " "+realOperation+ " "+secondNumber +" = ? ");
                }
            }
        }

        int position = new Random().nextInt(3) + 1;

        if(position == 1){
            buttonOpt1.setText(""+rightAnswer);
            buttonOpt2.setText(""+optionA);
            buttonOpt3.setText(""+optionB);
        }
        else
        {
            buttonOpt1.setText(""+optionA);
            if(position == 2)
            {
                buttonOpt2.setText(""+rightAnswer);
                buttonOpt3.setText(""+optionB);
            }
            else
            {
                buttonOpt3.setText(""+rightAnswer);
                buttonOpt2.setText(""+optionB);
            }
        }

        buttonOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonOpt1.getText().equals(""+rightAnswer))
                {
                    buttonOpt1.setBackgroundResource(R.drawable.right_answer_bg);
                    great = great + 1;
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    textRightAnswered.setText("RA : "+ great + " / 10");
                }else{
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    buttonOpt1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                long delayMillis = 1000;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if(level < 10)
                        {
                            getARandomQuestion();
                        }else{
                            Intent intent = new Intent(MathQuizActivity.this, MathQuizResultActivity.class);
                            intent.putExtra("RA", great);
                            startActivity(intent);
                            finish();
                        }
                    }
                },delayMillis);
            }
        });

        buttonOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonOpt2.getText().equals(""+rightAnswer))
                {
                    buttonOpt2.setBackgroundResource(R.drawable.right_answer_bg);
                    great = great + 1;
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    textRightAnswered.setText("RA : "+ great + " / 10");
                }else{
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    buttonOpt2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                long delayMillis = 1000;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if(level < 10)
                        {
                            getARandomQuestion();
                        }else{
                            Intent intent = new Intent(MathQuizActivity.this, MathQuizResultActivity.class);
                            intent.putExtra("RA", great);
                            startActivity(intent);
                            finish();
                        }
                    }
                },delayMillis);
            }
        });

        buttonOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonOpt3.getText().equals(""+rightAnswer))
                {
                    buttonOpt3.setBackgroundResource(R.drawable.right_answer_bg);
                    great = great + 1;
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    textRightAnswered.setText("RA : "+ great + " / 10");
                }else{
                    level = level + 1;
                    textlevel.setText(" Q : "+ level + " / 10");
                    buttonOpt3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                long delayMillis = 1000;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        if(level < 10)
                        {
                            getARandomQuestion();
                        }else{
                            Intent intent = new Intent(MathQuizActivity.this, MathQuizResultActivity.class);
                            intent.putExtra("RA", great);
                            startActivity(intent);
                            finish();
                        }
                    }
                },delayMillis);
            }
        });
    }
}