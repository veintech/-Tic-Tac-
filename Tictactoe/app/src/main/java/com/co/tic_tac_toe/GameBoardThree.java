package com.co.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Leo Py on 4/17/2018.
 */

public class GameBoardThree extends AppCompatActivity {

    // The game's internal state
    private TicTacToeGame mGame;

    // The Board Buttons
    private Button mBoardButtons[];
    private Button playAgain;

    // Displayed texts
    private TextView mInfoTextView;
    private TextView mPlayerOneCount;
    private TextView mTieCount;
    private TextView mPlayerTwoCount;
    private TextView mPlayerOneText;
    private TextView mPlayerTwoText;
    private TextView mBack;
    private TextView mReset;

    // Counter za wins and draws
    private int mPlayerOneCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerTwoCounter = 0;

    // These booleans for If player one goes first
    // if the gameType is to be single or dual player
    // if it's player one's turn
    // and if it's GAME OVER
    private boolean mPlayerOneFirst = true;
    private boolean mIsSinglePlayer = false;
    private boolean mIsPlayerOneTurn = true;
    private boolean mGameOver = false;
    boolean mMarkerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_3x3);
        boolean mGameType = getIntent().getExtras().getBoolean("gameType");
        mMarkerType = getIntent().getExtras().getBoolean("markerType");

        // Initialize the buttons
        mBoardButtons = new Button[mGame.getBOARD_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
        addListenerOnButton();

        // setup the textviews
        mInfoTextView = (TextView) findViewById(R.id.information);
        mPlayerOneCount = (TextView) findViewById(R.id.humanCount);
        mTieCount = (TextView) findViewById(R.id.tiesCount);
        mPlayerTwoCount = (TextView) findViewById(R.id.androidCount);
        mPlayerOneText = (TextView) findViewById(R.id.human);
        mPlayerTwoText = (TextView) findViewById(R.id.android);

        // set the initial counter display values
        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));

        // create a new game object
        mGame = new TicTacToeGame();

        // start a new game
        startNewGame(mGameType);

    }


    public void addListenerOnButton(){

        playAgain = findViewById(R.id.play_again);

        playAgain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startNewGame(mIsSinglePlayer);
            }
        });

        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mReset = findViewById(R.id.reset_game);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame(mIsSinglePlayer);
            }
        });
    }

    // Reset game
    // Clears board and resets scoreboard to nil values
    private void resetGame(boolean isSingle) {
        // set the initial counter display values
        mPlayerOneCounter = 0;
        mTieCounter = 0;
        mPlayerTwoCounter = 0;

        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
        startNewGame(isSingle);
    }
    // start a new game
    // clears the board and resets all buttons / text
    // sets game over to be false
    private void startNewGame(boolean isSingle)
    {

        this.mIsSinglePlayer = isSingle;

        mGame.clearBoard();

        for (int i = 0; i < mBoardButtons.length; i++)
        {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
            mBoardButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.blank));
        }


        if (mIsSinglePlayer)
        {
            mPlayerOneText.setText("Human:");
            mPlayerTwoText.setText("Android:");

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.first_human);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_computer);
                int move = mGame.getComputerMove();
                setMove(mGame.PLAYER_TWO, move);
                mPlayerOneFirst = true;
            }
        }
        else
        {
            mPlayerOneText.setText("Player One:");
            mPlayerTwoText.setText("Player Two:");

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.turn_player_one);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_player_two);
                mPlayerOneFirst = true;
            }
        }

        mGameOver = false;
    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        public ButtonClickListener(int location)
        {
            this.location = location;
        }

        public void onClick(View view)
        {
            if (!mGameOver)
            {
                if (mBoardButtons[location].isEnabled())
                {
                    if (mIsSinglePlayer)
                    {
                        setMove(mGame.PLAYER_ONE, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            mInfoTextView.setText(R.string.turn_computer);
                            int move = mGame.getComputerMove();
                            setMove(mGame.PLAYER_TWO, move);
                            winner = mGame.checkForWinner();
                        }

                        if (winner == 0)
                            mInfoTextView.setText(R.string.turn_human);
                        else if (winner == 1)
                        {
                            mInfoTextView.setText(R.string.result_tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            mInfoTextView.setText(R.string.result_human_wins);
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                        }
                        else
                        {
                            mInfoTextView.setText(R.string.result_android_wins);
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                        }
                    }
                    else
                    {
                        if (mIsPlayerOneTurn)
                            setMove(mGame.PLAYER_ONE, location);
                        else
                            setMove(mGame.PLAYER_TWO, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            if (mIsPlayerOneTurn)
                            {
                                mInfoTextView.setText(R.string.turn_player_two);
                                mIsPlayerOneTurn = false;
                            }
                            else
                            {
                                mInfoTextView.setText(R.string.turn_player_one);
                                mIsPlayerOneTurn = true;
                            }
                        }
                        else if (winner == 1)
                        {
                            mInfoTextView.setText(R.string.result_tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            mInfoTextView.setText(R.string.result_player_one_wins);
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = false;
                        }
                        else
                        {
                            mInfoTextView.setText(R.string.result_player_two_wins);
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = true;
                        }
                    }
                }
            }
        }
    }

    // set move for the current player
    private void setMove(char player, int location) {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        if (player == mGame.PLAYER_ONE) {

            if (mMarkerType) {
                mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.x));
            } else {
                mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.o));
            }
        } else {
            if (mMarkerType) {
                mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.o));
            } else {
                mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.x));
            }
        }
    }

}