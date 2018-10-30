package com.example.xinruigao.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private int turnCount;

    private boolean player1Turn = true;

    private int player1Score;
    private int player2Score;

    private TextView player1PointsTextView;
    private TextView player2PointsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                String buttonId = "button" + i + j;
                int resID = getResources().getIdentifier(buttonId,"id",getPackageName());
                //dynamically setting R.id.idOfButton
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                //implement onClick Listener for all Tic Tac Toe Buttons
            }
        }
        player1PointsTextView = findViewById(R.id.playerOneScoreTextView);
        player2PointsTextView = findViewById(R.id.playerTwoScoreTextView);
        final Button resetAllButton = findViewById(R.id.resetAllButton);
        resetAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        Button resetFieldButton = findViewById(R.id.resetFieldButton);
        resetFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) { //onClickListener of all Tic Tac Toe Buttons
        if(!((Button)v).getText().toString().equals("")){ //if Button is already occupied, skip
            return;
        }
        if(player1Turn){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        turnCount++;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else {
                player2Wins();
            }
        }
        else if(turnCount==9){
            draw();
        }
        else {
            player1Turn =!player1Turn;
            //set Player1 turn to opposite(set true to false and vice versa) to allow Player 2 turn

        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (field[0][j].equals(field[1][j])
                    && field[0][j].equals(field[2][j])
                    && !field[0][j].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[2][0].equals(field[1][1])
                && field[2][0].equals(field[0][2])
                && !field[2][0].equals("")) {
            return true;
        }
        else {
            return false;
        }
    }

    private void player1Wins(){
        player1Score++;
        Toast.makeText(this, "PLAYER 1 WINS", Toast.LENGTH_LONG).show();
        updatePointsTextView();


    }
    private void player2Wins(){
        player2Score++;
        Toast.makeText(this, "PLAYER 2 WINS", Toast.LENGTH_LONG).show();
        updatePointsTextView();


    }
    private void draw(){
        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();


    }

    private void updatePointsTextView(){
        player1PointsTextView.setText("Player one points: " + player1Score);
        player2PointsTextView.setText("Player two points: " + player2Score);
    }
    private void resetBoard(){
        for (int i =0; i<3; i++){
            for (int j=0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        turnCount=0;
        player1Turn=true;

    }
    private void resetGame(){
        resetBoard();
        player1Score=0;
        player2Score=0;
        updatePointsTextView();

    }


}
