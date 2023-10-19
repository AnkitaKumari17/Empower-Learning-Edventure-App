package com.myapp.empoweringlearningedventure;

import static com.myapp.empoweringlearningedventure.R.id.btnNewGame3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Bitmap> imageParts;
    private List<Integer> solvedImageParts;
    private ImageView[] imageViews;
    private int emptyImageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageViews = new ImageView[9];
        imageViews[0] = findViewById(R.id.image1);
        imageViews[1] = findViewById(R.id.image2);
        imageViews[2] = findViewById(R.id.image3);
        imageViews[3] = findViewById(R.id.image4);
        imageViews[4] = findViewById(R.id.image5);
        imageViews[5] = findViewById(R.id.image6);
        imageViews[6] = findViewById(R.id.image7);
        imageViews[7] = findViewById(R.id.image8);
        imageViews[8] = findViewById(R.id.image9);

        for (ImageView imageView : imageViews) {
            imageView.setOnClickListener(this);
        }

        Bitmap fullImage = BitmapFactory.decodeResource(getResources(), R.drawable.full_apple);
        imageParts = splitImage(fullImage);
        solvedImageParts = new ArrayList<>(imageParts.size());
        for (int i = 0; i < imageParts.size(); i++) {
            solvedImageParts.add(i);
        }
        startNewGame();
    }

    private List<Bitmap> splitImage(Bitmap image) {
        int rows = 3;
        int columns = 3;
        int width = image.getWidth();
        int height = image.getHeight();

        int partWidth = width / columns;
        int partHeight = height / rows;

        List<Bitmap> imageParts = new ArrayList<>(rows * columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = j * partWidth;
                int y = i * partHeight;
                Bitmap part = Bitmap.createBitmap(image, x, y, partWidth, partHeight);
                imageParts.add(part);
            }
        }

        return imageParts;
    }

    private void shuffleImageParts() {
        Collections.shuffle(solvedImageParts);
        for (int i = 0; i < solvedImageParts.size(); i++) {
            int index = solvedImageParts.get(i);
            imageViews[i].setImageBitmap(imageParts.get(index));
            if (index == solvedImageParts.size() - 1) {
                emptyImageIndex = i;
            }
        }
    }

    private void startNewGame() {
        shuffleImageParts();
    }

    private boolean isGameSolved() {
        for (int i = 0; i < solvedImageParts.size(); i++) {
            if (solvedImageParts.get(i) != i) {
                return false;
            }
        }
        return true;
    }

    private void moveImage(int position) {
        int row = position / 3;
        int col = position % 3;

        if (col > 0 && emptyImageIndex == position - 1) {
            // Move left
            Collections.swap(solvedImageParts, position, emptyImageIndex);
            imageViews[position].setImageBitmap(imageParts.get(solvedImageParts.get(position)));
            imageViews[emptyImageIndex].setImageBitmap(imageParts.get(solvedImageParts.get(emptyImageIndex)));
            emptyImageIndex = position;
        } else if (col < 2 && emptyImageIndex == position + 1) {
            // Move right
            Collections.swap(solvedImageParts, position, emptyImageIndex);
            imageViews[position].setImageBitmap(imageParts.get(solvedImageParts.get(position)));
            imageViews[emptyImageIndex].setImageBitmap(imageParts.get(solvedImageParts.get(emptyImageIndex)));
            emptyImageIndex = position;
        } else if (row > 0 && emptyImageIndex == position - 3) {
            // Move up
            Collections.swap(solvedImageParts, position, emptyImageIndex);
            imageViews[position].setImageBitmap(imageParts.get(solvedImageParts.get(position)));
            imageViews[emptyImageIndex].setImageBitmap(imageParts.get(solvedImageParts.get(emptyImageIndex)));
            emptyImageIndex = position;
        } else if (row < 2 && emptyImageIndex == position + 3) {
            // Move down
            Collections.swap(solvedImageParts, position, emptyImageIndex);
            imageViews[position].setImageBitmap(imageParts.get(solvedImageParts.get(position)));
            imageViews[emptyImageIndex].setImageBitmap(imageParts.get(solvedImageParts.get(emptyImageIndex)));
            emptyImageIndex = position;
        }

        if (isGameSolved()) {
            Toast.makeText(this, "Congratulations! You solved the puzzle.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < imageViews.length; i++) {
            if (v == imageViews[i]) {
                moveImage(i);
                break;
            }
        }
    }
}