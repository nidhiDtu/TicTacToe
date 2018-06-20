package com.example.user.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static int PLAYER_O=1;
    public final static int PLAYER_X=2;
    public final static int NO_PLAYER=-1;

    private int currentPlayer;
    private boolean GameOver;
    private TextView playerNo;
    private LinearLayout rootLayout;
    private TTTButton board[][];
    private int Size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        int size = intent.getIntExtra("size_key",3);
        Size=size;
        initialise();
        setUpBoard();
    }

    private void setUpBoard() {

        for(int i=0;i<Size;i++){
            LinearLayout rowLayout=new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams rowLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            rowLayout.setLayoutParams(rowLayoutParams);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for(int j=0;j<Size;j++){
                TTTButton button=new TTTButton(this);
                LinearLayout.LayoutParams buttonParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(buttonParams);

                button.setOnClickListener(this);
                rowLayout.addView(button);
                board[i][j]=button;
            }
            rootLayout.addView(rowLayout);
        }
    }

    private void togglePlayer() {
        if(currentPlayer==NO_PLAYER || currentPlayer==PLAYER_X){
            currentPlayer=PLAYER_O;
        }else if(currentPlayer==PLAYER_O){
            currentPlayer=PLAYER_X;
        }
//        playerNo.setText(currentPlayer+"");
    }

    private void checkGameOver() {

        for(int i=0;i<Size;i++){
            boolean isSame=true;
            TTTButton rowFirstButton=board[i][0];
            for(int j=0;j<Size;j++){
                TTTButton currentButton=board[i][j];
                if(rowFirstButton.getPlayer()!=currentButton.getPlayer() || rowFirstButton.isEmpty()){
                    isSame=false;
                    break;
                }
            }
            if(isSame){
                int winner=rowFirstButton.getPlayer();
                onGameOver(winner);
                return;
            }
        }

        for (int j=0;j<Size;j++){
            boolean isSame=true;
            TTTButton columnFirstButton=board[0][j];
            for (int i=0;i<Size;i++){
                TTTButton currentButton=board[i][j];
                if(currentButton.isEmpty() || currentButton.getPlayer()!=columnFirstButton.getPlayer()){
                    isSame=false;
                    break;
                }
            }
            if(isSame){
                int winner=columnFirstButton.getPlayer();
                onGameOver(winner);
                return;
            }
        }

        //TODO FOR DIAGONALS
        boolean isSame1=true;
        TTTButton firstButton1=board[0][Size-1];
        for (int i=0;i<Size;i++){
            TTTButton currentButton=board[i][Size-1-i];
            if(currentButton.isEmpty() || currentButton.getPlayer()!=firstButton1.getPlayer()){
                isSame1=false;
                break;
            }
        }
        if(isSame1){
            int winner=firstButton1.getPlayer();
            onGameOver(winner);
            return;
        }

        boolean isSame2=true;
        TTTButton firstButton2=board[0][0];
        for (int i=0;i<Size;i++){
            TTTButton currentButton=board[i][i];
            if(currentButton.isEmpty() || currentButton.getPlayer()!=firstButton2.getPlayer()){
                isSame2=false;
                break;
            }
        }
        if(isSame2){
            int winner=firstButton2.getPlayer();
            onGameOver(winner);
            return;
        }


        boolean full = true;
        for(int i = 0;i<Size;i++){
            for(int j = 0; j<Size; j++){
                if(board[i][j].isEmpty()){
                    full = false;
                    break;
                }
            }
        }
        if(full){
            onGameOver(NO_PLAYER);
        }

    }

    private void onGameOver(int winner) {

        if(winner==-1){
            Toast.makeText(this,"GAME DRAW!!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"The PLAYER : "+winner+" Won!!",Toast.LENGTH_LONG).show();
        }
        GameOver=true;
        playerNo.setText("GAME OVER!!");
    }

    private void initialise() {
        currentPlayer=NO_PLAYER;
        board=new TTTButton[Size][Size];
        playerNo=(TextView)findViewById(R.id.player);
        rootLayout=(LinearLayout)findViewById(R.id.rootLayout);
        GameOver=false;
        playerNo.setText("Turn : Player 1");
    }

    @Override
    public void onClick(View view) {
        TTTButton button = (TTTButton)view;
        if(!GameOver){
            togglePlayer();
            button.setPlayer(currentPlayer);
            button.setImage();
            checkGameOver();
            button.setEnabled(false);
            playerNo.setText("Turn : Player "+button.getPlayer()+"");
        }
    }
}
