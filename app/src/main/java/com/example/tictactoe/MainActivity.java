package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation:
    //0-X
    //1-O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //stateMeaning:
    //0-X
    //1-O
    //2-Null

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            return; // If the game is not active, don't process any further clicks
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.xt);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - tap to play");
            } else {
                img.setImageResource(R.drawable.ot);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        //check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {

                //somebody has won - find out who
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                //update the status bar for winner  announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        // After checking for a win condition
        boolean isDraw = true;
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 2) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            gameActive = false;
            TextView status = findViewById(R.id.status);
            status.setText("It's a draw!");
            updateRestartButtonVisibility();
        }
        updateRestartButtonVisibility();
    }
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - tap to play");
        updateRestartButtonVisibility();
    }
    private void updateRestartButtonVisibility() {
        Button restartButton = findViewById(R.id.restartButton);
        if (!gameActive) {
            restartButton.setVisibility(View.VISIBLE);
        } else {
            restartButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}