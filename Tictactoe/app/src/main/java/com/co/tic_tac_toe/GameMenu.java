package com.co.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by Leo Py on 4/17/2018.
 */

public class GameMenu extends AppCompatActivity{
    boolean gameType;
    boolean boardType;
    boolean markerType;

    RadioGroup player, board, marker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);

        //(findViewById(R.id.one_player)).setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View V) {
        //        Log.d("DEBUG", "One Player Button Pressed!");
        //        Intent intent = new Intent(GameMenu.this, GameBoardThree.class);
        //        intent.putExtra("gameType", true);
        //        startActivityForResult(intent, 0);
        //    }
        //});

        player = findViewById(R.id.select_player);
        board = findViewById(R.id.select_board);
        marker = findViewById(R.id.select_marker);

        player.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.one_player) {
                    gameType = true;
                } else {
                    gameType = false;
                }
            }
        });

        board.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.board_three) {
                    boardType = true;
                } else {
                    boardType = false;
                }
            }
        });

        marker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.x) {
                    markerType = true;
                } else {
                    markerType = false;
                }
            }
        });

        (findViewById(R.id.start_game)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Log.d("DEBUG", "Two Player Button Pressed!");
                if (boardType) {
                    Intent intent = new Intent(GameMenu.this, GameBoardThree.class);
                    intent.putExtra("gameType", gameType);
                    intent.putExtra("markerType", markerType);
                    startActivityForResult(intent, 0);
                } else {
                    Intent intent = new Intent(GameMenu.this, GameBoardFive.class);
                    intent.putExtra("gameType", gameType);
                    intent.putExtra("markerType", markerType);
                    startActivityForResult(intent, 0);
                }

            }
        });

        (findViewById(R.id.exit_game)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Log.d("DEBUG", "Exit Game Button Pressed!");
                GameMenu.this.finish();
            }
        });
    }

}
