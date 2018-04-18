package com.co.tic_tac_toe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Leo Py on 4/18/2018.
 */

public class GameBoardFive extends AppCompatActivity {

    // The game's internal state
    private TicTacFive mGame;

    // The Board Buttons
    private Button playAgain;
    private TextView mBack, mReset;
    private Button mBoardButtons[];

    // Displayed texts
    private TextView mInfoTextView;
    private TextView mPlayerOneCount;
    private TextView mTieCount;
    private TextView mPlayerTwoCount;
    private TextView mPlayerOneText;
    private TextView mPlayerTwoText;

    // Counter za wins and draws
    private int mPlayerOneCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerTwoCounter = 0;

    // These booleans for If player one goes first
    // if the gameType is to be single or dual player
    // if it's player one's turn
    // and if it's GAME OVER
    private boolean mPlayerOneFirst = true;
    private boolean mIsSinglePlayer = true;
    private boolean mIsPlayerOneTurn = true;
    private boolean mGameOver = false;
    boolean mMarkerType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_5x5);
        boolean mGameType = getIntent().getExtras().getBoolean("gameType");
        mMarkerType = getIntent().getExtras().getBoolean("markerType");
        // Initialize the buttons
        mBoardButtons = new Button[mGame.getBOARD_SIZE()];
        mBoardButtons[0] = findViewById(R.id.one);
        mBoardButtons[1] = findViewById(R.id.two);
        mBoardButtons[2] = findViewById(R.id.three);
        mBoardButtons[3] = findViewById(R.id.four);
        mBoardButtons[4] = findViewById(R.id.five);
        mBoardButtons[5] = findViewById(R.id.six);
        mBoardButtons[6] = findViewById(R.id.seven);
        mBoardButtons[7] = findViewById(R.id.eight);
        mBoardButtons[8] = findViewById(R.id.nine);
        mBoardButtons[9] = findViewById(R.id.ten);
        mBoardButtons[10] = findViewById(R.id.eleven);
        mBoardButtons[11] = findViewById(R.id.twelve);
        mBoardButtons[12] = findViewById(R.id.thirteen);
        mBoardButtons[13] = findViewById(R.id.fourteen);
        mBoardButtons[14] = findViewById(R.id.fifteen);
        mBoardButtons[15] = findViewById(R.id.sixteen);
        mBoardButtons[16] = findViewById(R.id.seventeen);
        mBoardButtons[17] = findViewById(R.id.eighteen);
        mBoardButtons[18] = findViewById(R.id.nineteen);
        mBoardButtons[19] = findViewById(R.id.twenty);
        mBoardButtons[20] = findViewById(R.id.twentyOne);
        mBoardButtons[21] = findViewById(R.id.twentyTwo);
        mBoardButtons[22] = findViewById(R.id.twentyThree);
        mBoardButtons[23] = findViewById(R.id.twentyFour);
        mBoardButtons[24] = findViewById(R.id.twentyFive);

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
        mGame = new TicTacFive();

        // start a new game
        startNewGame(mGameType);
    }


    private void startNewGame(boolean isSingle) {
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

    public void addListenerOnButton(){

        playAgain = findViewById(R.id.play_again);

        playAgain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
    private void setMove(char player, int location)
    {
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
