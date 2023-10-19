package com.myapp.empoweringlearningedventure;

import static com.myapp.empoweringlearningedventure.R.*;
import static com.myapp.empoweringlearningedventure.R.id.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PicPuzzleActivity extends AppCompatActivity {
    private static final int columns = 3;
    private static final int dimensions = columns * columns;
    private static String[] tileList;
    private static GestureDetectGridView mGridView;
    private static int mColumnWidth, mColumnHeight;
    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";
    private static Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_pic_puzzle);

        next = findViewById(btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PicPuzzleActivity.this, "Next button has been clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PicPuzzleActivity.this, PicPuzzleResultActivity.class);
                startActivity(intent);
                finish();
            }
        });
        init();
        scramble();
        setDimensions();
    }

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(grid);
        mGridView.setNumColumns(columns);
        tileList = new String[dimensions];
        for (int i = 0; i < dimensions; i++)
        {
            tileList[i]= String.valueOf(i);
        }
    }

    private void scramble()
    {
        int index;
        String temp;
        Random random = new Random();

        for(int i= tileList.length -1; i>0; i--)
        {
            index= random.nextInt(i+1);
            temp= tileList[index];
            tileList[index]= tileList[i];
            tileList[i]= temp;
        }
    }
    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / columns;
                mColumnHeight = requiredHeight / columns;

                display(getApplicationContext());
            }
        });
    }
    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static void display(Context context)
    {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for( int i=0; i<tileList.length; i++)
        {
            button = new Button(context);

            if(tileList[i].equals("0"))
                button.setBackgroundResource(drawable.apple_pic1);
            else if(tileList[i].equals("1"))
                button.setBackgroundResource(drawable.apple_pic2);
            else if(tileList[i].equals("2"))
                button.setBackgroundResource(drawable.apple_pic3);
            else if(tileList[i].equals("3"))
                button.setBackgroundResource(drawable.apple_pic4);
            else if(tileList[i].equals("4"))
                button.setBackgroundResource(drawable.apple_pic5);
            else if(tileList[i].equals("5"))
                button.setBackgroundResource(drawable.apple_pic6);
            else if(tileList[i].equals("6"))
                button.setBackgroundResource(drawable.apple_pic7);
            else if(tileList[i].equals("7"))
                button.setBackgroundResource(drawable.apple_pic8);
            else if(tileList[i].equals("8"))
                button.setBackgroundResource(drawable.apple_pic9);

            buttons.add(button);
        }
        mGridView.setAdapter(new CustomerAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved())
        {
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            next.setVisibility(View.VISIBLE);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < columns - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == columns - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > columns - 1 && position < dimensions - columns&& position % columns == 0) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, columns);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == columns * 2 - 1 || position == columns * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= dimensions - columns - 1) swap(context, position, columns);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == dimensions - columns) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < dimensions - 1 && position > dimensions - columns) {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -columns);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, columns);
        }
    }
}