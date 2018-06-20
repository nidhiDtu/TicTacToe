package com.example.user.tictactoe;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by user on 1/30/2018.
 */

public class TTTButton extends AppCompatButton{

    private int player=MainActivity.NO_PLAYER;

    public TTTButton(Context context){
        super(context);
        setBackgroundResource(R.drawable.button_bg);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int pl){
        this.player=pl;
//        switch (pl){
//            case MainActivity.PLAYER_O:
//                this.setText("O");
//                break;
//            case MainActivity.PLAYER_X:
//                this.setText("X");
//                break;
//        }
    }

    public void setImage(){
        if(player==MainActivity.PLAYER_O) {
            this.setBackgroundResource(R.drawable.o_icon);
        }
        else if(player==MainActivity.PLAYER_X){
            this.setBackgroundResource(R.drawable.x_icon);
        }else{
            return;
        }
    }

    public boolean isEmpty() {
        if(this.player==MainActivity.NO_PLAYER){
            return true;
        }
        return false;
    }
}
