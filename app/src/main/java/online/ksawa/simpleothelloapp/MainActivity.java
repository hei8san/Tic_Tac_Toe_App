package online.ksawa.simpleothelloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    char[][] grid;
    boolean red = true;
    TextView result;
    int count;
    static int numberOfRowCol = 3;

    public void imageClick(View view) {
        Log.i("Info", "Image clicked");
        ImageView imageView = findViewById(view.getId());
        Log.i("Info", imageView.getTag().toString());
        int row = Integer.parseInt(imageView.getTag().toString()) / numberOfRowCol;
        int col = Integer.parseInt(imageView.getTag().toString()) % numberOfRowCol;
        if (!isImageSet(grid, row, col)) {
            count++;
            if (red) {
                red = false;
                grid[row][col] = 'r';
                imageView.setTranslationY(-1500);
                imageView.animate().setDuration(1000).translationYBy(1500);
                imageView.setImageResource(R.drawable.red_circle);
            } else {
                red = true;
                grid[row][col] = 'y';
                imageView.setTranslationY(-1500);
                imageView.animate().setDuration(1000).translationYBy(1500);
                imageView.setImageResource(R.drawable.yellow_circle);
            }
        } else {
            //already image set
            result.setText("Already placed. Choose another black space");
        }
        if (judgeWining(grid)) {
            StringBuilder winner = new StringBuilder();
            if (red) {
                winner.append("Red");
            } else {
                winner.append("Yellow");
            }
            result.setText(winner + " win!!");
        }
    }

    public void resetBTNClicked(View view) {
//        Button resetBTN = findViewById(view.getId());
//        grid = new char[3][3];
        Log.i("Info", "reset BTN clicked");
    }

    public boolean judgeWining(char[][] grid) {
        int numberOfGird = (int) (Math.pow((double) numberOfRowCol, (double) 2));
        Log.i("Info", "number of Grid: " + Integer.toString(numberOfGird));
        int count = numberOfGird - (numberOfGird / 2);
        Log.i("Info", "count: " + Integer.toString(count));

        boolean isRowCheck = true;
        boolean isColCheck = true;
        boolean leftToRight = true;
        boolean rightToLeft = true;
//        while(count >= 0){
//            count--;
        for (int row = 0; row < numberOfRowCol; row++) {
            isRowCheck = true;
            char rowFirstChar = ' ';
            isColCheck = true;
            char colFirstChar = ' ';
            for (int col = 0; col < numberOfRowCol; col++) {
                if (col == 0) {
                    rowFirstChar = grid[row][col];
                    colFirstChar = grid[col][row];
                    continue;
                }
                if (grid[row][col] != rowFirstChar) {
                    isRowCheck = false;
                }
                if (grid[col][row] != colFirstChar) {
                    isColCheck = false;
                }
            }
            if (isColCheck || isRowCheck) {
                return true;
            }
//            }
        }
        return false;
    }

    public boolean isImageSet(char[][] grid, int row, int col) {
        if (grid[row][col] == 'r' || grid[row][col] == 'y') {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = new char[numberOfRowCol][numberOfRowCol];
        Log.i("Info", "char 2d array created");
        result = findViewById(R.id.resultText);
        count = 0;

    }
}