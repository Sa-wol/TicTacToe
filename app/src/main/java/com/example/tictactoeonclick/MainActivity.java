package com.example.tictactoeonclick;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final int[] btn_id = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private final Button[] btn = new Button[9];
    private boolean playerXTurn = true;
    private int turnDecision = 0;
    TextView playerX;
    TextView playerO;
    TextView playerTie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.tic_tac_toe);
    }

    // Player X or O places their respective markers on the button
    // X and O can't be modified once clicked unless new game button is clicked.
    public void onClick(View v) {
        if (!(((Button) v).getText().toString().equals(""))) {
           return;
        }

        if(playerXTurn) {
            playerXTurn = false;
            ((Button) v).setText("X");
            playerOTurn();
        } else {
            playerXTurn = true;
            ((Button) v).setText("O");
            playerXTurn();
        }

        turnDecision++;

        if(checkForXWin() || checkForOWin()) {
            if(!playerXTurn) {
                playerXWins(v);
            } else {
                playerOWins(v);
            }
        } else if(turnDecision == 9) {
            playerTie();
        }
    }

    public boolean checkForOWin() {
        TextView btnText = findViewById(R.id.btn1);
        TextView btnText2 = findViewById(R.id.btn2);
        TextView btnText3 = findViewById(R.id.btn3);
        TextView btnText4 = findViewById(R.id.btn4);
        TextView btnText5 = findViewById(R.id.btn5);
        TextView btnText6 = findViewById(R.id.btn6);
        TextView btnText7= findViewById(R.id.btn7);
        TextView btnText8 = findViewById(R.id.btn8);
        TextView btnText9 = findViewById(R.id.btn9);

        // Horizontal wins
        if(btnText.getText().toString().equals("O")
                && btnText2.getText().toString().equals("O")
                && btnText3.getText().toString().equals("O") ||
                btnText4.getText().toString().equals("O")
                        && btnText5.getText().toString().equals("O")
                        && btnText6.getText().toString().equals("O") ||
                btnText7.getText().toString().equals("O")
                        && btnText8.getText().toString().equals("O")
                        && btnText9.getText().toString().equals("O")){
            return true;
        }

        // Vertical wins
        if(btnText.getText().toString().equals("O")
                && btnText4.getText().toString().equals("O")
                && btnText7.getText().toString().equals("O") ||
                btnText2.getText().toString().equals("O")
                        && btnText5.getText().toString().equals("O")
                        && btnText8.getText().toString().equals("O") ||
                btnText3.getText().toString().equals("O")
                        && btnText6.getText().toString().equals("O")
                        && btnText9.getText().toString().equals("O")){
            return true;
        }

        // Diagonal wins
        if(btnText.getText().toString().equals("O")
                && btnText5.getText().toString().equals("O")
                && btnText9.getText().toString().equals("O") ||
                btnText3.getText().toString().equals("O")
                        && btnText5.getText().toString().equals("O")
                        && btnText7.getText().toString().equals("O")){
            return true;
        }
        return false;
    }

    // Checks if Player X or O wins the game and resets the game after
    public boolean checkForXWin() {
        TextView btnText = findViewById(R.id.btn1);
        TextView btnText2 = findViewById(R.id.btn2);
        TextView btnText3 = findViewById(R.id.btn3);
        TextView btnText4 = findViewById(R.id.btn4);
        TextView btnText5 = findViewById(R.id.btn5);
        TextView btnText6 = findViewById(R.id.btn6);
        TextView btnText7= findViewById(R.id.btn7);
        TextView btnText8 = findViewById(R.id.btn8);
        TextView btnText9 = findViewById(R.id.btn9);

        // Horizontal wins
        if(btnText.getText().toString().equals("X")
            && btnText2.getText().toString().equals("X")
            && btnText3.getText().toString().equals("X") ||
                btnText4.getText().toString().equals("X")
                && btnText5.getText().toString().equals("X")
                && btnText6.getText().toString().equals("X") ||
                    btnText7.getText().toString().equals("X")
                        && btnText8.getText().toString().equals("X")
                        && btnText9.getText().toString().equals("X")){
            return true;
        }

        // Vertical wins
        if(btnText.getText().toString().equals("X")
                && btnText4.getText().toString().equals("X")
                && btnText7.getText().toString().equals("X") ||
                btnText2.getText().toString().equals("X")
                        && btnText5.getText().toString().equals("X")
                        && btnText8.getText().toString().equals("X") ||
                btnText3.getText().toString().equals("X")
                        && btnText6.getText().toString().equals("X")
                        && btnText9.getText().toString().equals("X")){
            return true;
        }

        // Diagonal wins
        if(btnText.getText().toString().equals("X")
                && btnText5.getText().toString().equals("X")
                && btnText9.getText().toString().equals("X") ||
                btnText3.getText().toString().equals("X")
                        && btnText5.getText().toString().equals("X")
                        && btnText7.getText().toString().equals("X")){
            return true;
        }
        return false;
    }

    // Player X wins the game
    public void playerXWins(View v) {
        playerX = findViewById(R.id.playerTurnTxt);
        playerX.setText("Player X wins!");

        for(int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            String btnText = btn[i].getText().toString();
            if(btnText.equals("")) {
                btn[i].setEnabled(false);
            }
        }

    }

    // Player O wins the game
    public void playerOWins(View v) {
        playerO = findViewById(R.id.playerTurnTxt);
        playerO.setText("Player O wins!");

        // Disables empty buttons that were not clicked
        for(int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            String btnText = btn[i].getText().toString();
            if(btnText.equals("")) {
                btn[i].setEnabled(false);
            }
        }
    }

    // Player X and O tie in the game
    public void playerTie() {
        playerTie = findViewById(R.id.playerTurnTxt);
        playerTie.setText("It's a tie!");
    }

    // Player O's turn
    public void playerOTurn() {
        playerO = findViewById(R.id.playerTurnTxt);
        playerO.setText("Player O's turn");
    }

    // Player X's turn
    public void playerXTurn() {
        playerX = findViewById(R.id.playerTurnTxt);
        playerX.setText("Player X's turn");
    }

    // Resets buttons back to blank buttons
    public void resetButtons(View v) {
        // clear buttons
        // reset to player X's turn
        for(int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            String btnText = btn[i].getText().toString();
            if(!btnText.equals("")) {
                btn[i].setText("");
            }
            else {
                btn[i].setEnabled(true);
            }

            playerXTurn = true;
            turnDecision = 0;
        }
    }

    // Clears the buttons of X and Os for a new game
    // Resets back to Player X's turn
    public void resetGameOnClick(View v) {
        resetButtons(v);
        playerXTurn();
    }
}