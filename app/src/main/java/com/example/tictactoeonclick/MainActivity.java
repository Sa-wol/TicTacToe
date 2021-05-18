package com.example.tictactoeonclick;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[] btn = new Button[9];
    private int[] btn_id = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    private boolean playerX = true;
    private int turnDecision = 1;

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
        if (!(((Button) v).getText().toString() == "")) {
            ((Button) v).setClickable(false);
        }
        else if(playerX) {
            playerX = false;
            ((Button) v).setText("X");
            playerOTurn();
        }
        else {
            playerX = true;
            ((Button) v).setText("O");
            playerXTurn();
        }
            turnDecision++;
            ((Button) v).setClickable(true);
    }

    // Player O's turn
    public void playerOTurn() {
        TextView oTurn = findViewById(R.id.playerTurnTxt );
        oTurn.setText("Player O's turn");
    }

    // Player X's turn
    public void playerXTurn() {
        TextView xTurn = findViewById(R.id.playerTurnTxt);
        xTurn.setText("Player X's turn");
    }

    // Clears the buttons of X and Os for a new game
    public void newGameOnClick(View v) {
        // clear buttons
        // reset to player X's turn
        for(int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btn_id[i]);
            String btnText = btn[i].getText().toString();
            if (!btnText.equals("")) {
                btn[i].setText("");
                playerXTurn();
                playerX = true;
            }
        }
    }
}