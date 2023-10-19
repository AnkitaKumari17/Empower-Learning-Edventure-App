package com.myapp.empoweringlearningedventure;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MathPuzzleActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[4][4];
    private Button newGameButton;
    private int[][] puzzleGrid = new int[4][4];
    private int emptyRow, emptyCol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_puzzle);

        newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);

        initializeButtons();
        initializePuzzle();
    }
    private void initializeButtons() {
        buttons[0][0] = findViewById(R.id.button1);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[0][3] = findViewById(R.id.button4);
        buttons[1][0] = findViewById(R.id.button5);
        buttons[1][1] = findViewById(R.id.button6);
        buttons[1][2] = findViewById(R.id.button7);
        buttons[1][3] = findViewById(R.id.button8);
        buttons[2][0] = findViewById(R.id.button9);
        buttons[2][1] = findViewById(R.id.button10);
        buttons[2][2] = findViewById(R.id.button11);
        buttons[2][3] = findViewById(R.id.button12);
        buttons[3][0] = findViewById(R.id.button13);
        buttons[3][1] = findViewById(R.id.button14);
        buttons[3][2] = findViewById(R.id.button15);
        buttons[3][3] = findViewById(R.id.button16);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    private void initializePuzzle() {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        Collections.shuffle(numbers);

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                puzzleGrid[i][j] = numbers.get(index);
                if (numbers.get(index) == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
                buttons[i][j].setText(String.valueOf(numbers.get(index)));
                index++;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_game_button) {
            initializePuzzle();
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (v.getId() == buttons[i][j].getId()) {
                    if (isAdjacentToEmptyTile(i, j)) {
                        swapWithEmptyTile(i, j);
                        checkIfPuzzleSolved();
                    } else {
                        Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }
    }

    private boolean isAdjacentToEmptyTile(int row, int col) {
        return ((Math.abs(row - emptyRow) == 1 && col == emptyCol) || (Math.abs(col - emptyCol) == 1 && row == emptyRow));
    }

    private void swapWithEmptyTile(int row, int col) {
        buttons[emptyRow][emptyCol].setText(buttons[row][col].getText());
        buttons[row][col].setText("");
        puzzleGrid[emptyRow][emptyCol] = puzzleGrid[row][col];
        puzzleGrid[row][col] = 0;
        emptyRow = row;
        emptyCol = col;
    }

    private void checkIfPuzzleSolved() {
        boolean solved = true;
        int number = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (puzzleGrid[i][j] != number && number != 16) {
                    solved = false;
                    break;
                }
                number++;
            }
        }
        if (solved) {
            Toast.makeText(this, "Congratulations! Puzzle solved!", Toast.LENGTH_SHORT).show();
        }
    }
}

