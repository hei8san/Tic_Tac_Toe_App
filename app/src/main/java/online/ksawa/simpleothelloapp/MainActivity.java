package online.ksawa.simpleothelloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    char[][] grid;
    boolean red = true;
    TextView result;
    int count;
    static int numberOfRowCol = 3;
    boolean gameActive = true;

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
        if (judgeWinning(grid) && gameActive) {
            StringBuilder winner = new StringBuilder();
            gameActive = false;
            if (!red) {
                winner.append("Red");
            } else {
                winner.append("Yellow");
            }
            result.setText(winner + " win!!");
        }
    }

    public void resetBTNClicked(View view) {
        Log.i("Info", "reset BTN clicked");
        GridLayout gridlayout = findViewById(R.id.gridLayoutBoard);
        for(int i = 0;i < gridlayout.getChildCount(); i++ )  {
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
        }
    }

    public boolean judgeWinning(char[][] grid) {
        int numberOfRowCol = grid.length;

        boolean isRowCheck;
        boolean isColCheck;
        boolean isLeftToRightDiagonalCheck = true;
        boolean isRightToLeftDiagonalCheck = true;

        Log.i("Info", Arrays.deepToString(grid));
        // Check rows and columns
        for (int i = 0; i < numberOfRowCol; i++) {
            isRowCheck = true;
            isColCheck = true;

            char rowFirstChar = grid[i][0];
            Log.i("Info", "rowFirstChar: "+Character.toString(rowFirstChar));
            char colFirstChar = grid[0][i];
            Log.i("Info", "colFirstChar: "+Character.toString(colFirstChar));


            for (int j = 0; j < numberOfRowCol; j++) {
                if (grid[i][j] != rowFirstChar) {
                    isRowCheck = false;
                }
                if (grid[j][i] != colFirstChar) {
                    isColCheck = false;
                }
            }

            if (isRowCheck || isColCheck) {
                return true;
            }
        }

        // Check diagonals
        char diagonalFirstChar = grid[0][0];
        for (int i = 0; i < numberOfRowCol; i++) {
            if (grid[i][i] != diagonalFirstChar) {
                isLeftToRightDiagonalCheck = false;
            }
            if (grid[i][numberOfRowCol - i - 1] != diagonalFirstChar) {
                isRightToLeftDiagonalCheck = false;
            }
        }

        if (isLeftToRightDiagonalCheck || isRightToLeftDiagonalCheck) {
            return true;
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
        for (int row = 0; row < numberOfRowCol; row++) {
            for (int col = 0; col < numberOfRowCol; col++) {
                char c = (char) ((char)row+col);
                grid[row][col] = c;
            }
        }
        Log.i("Info", "char 2d array created");
        result = findViewById(R.id.resultText);
        count = 0;

    }
    private void loadActivity() {
        // Do all of your work here
    }

    private View.OnClickListener ReloadActivity = new View.OnClickListener() {
        public void onClick(View v) {
            loadActivity();
        }
    };
}