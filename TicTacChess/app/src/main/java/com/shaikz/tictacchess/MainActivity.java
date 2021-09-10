package com.shaikz.tictacchess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaikz.tictacchess.R;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // 0--- white 1--- black
    boolean active = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void onClick(View view) {
        ImageView imageView = (ImageView) view;
        int tappedImage = Integer.parseInt(imageView.getTag().toString());
        if (gameState[tappedImage] == 2 && active) {
            gameState[tappedImage] = activePlayer;
            imageView.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.white_pawn);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.black_pawn);
                activePlayer = 0;
            }
            imageView.animate().translationYBy(1000f).rotation(3600f).setDuration(350);
            for(int[] winningPosition: winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){
                    String winner = "White";
                    active = false;
                    if(gameState[winningPosition[0]]==1) {
                        winner = "Black";
                    }
                    TextView winnerMsg = (TextView)findViewById(R.id.gameOverText);
                    winnerMsg.setText(winner + " has won!!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.linearlayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameGotOver = true;
                    for(int imageState:gameState){
                        if(imageState==2)
                            gameGotOver = false;
                    }
                    if(gameGotOver){
                        TextView winnerMsg = (TextView)findViewById(R.id.gameOverText);
                        winnerMsg.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.linearlayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
        layout.setVisibility(View.INVISIBLE);
        active = true;
        activePlayer = 0; // 0--- white 1--- black
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        for (int j = 0; j < gameState.length; j++) {
            gameState[j] = 2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
