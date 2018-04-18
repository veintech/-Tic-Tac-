package com.co.tic_tac_toe;

import java.util.Random;

/**
 * Created by Matt Smith Py on 4/18/2018.
 */

public class TicTacFive {

    private char mBoard[];
    private final static int BOARD_SIZE = 25;

    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = '0';
    public static final char EMPTY_SPACE = ' ';

    private Random mRand;

    private int value;
    public static int getBOARD_SIZE() {
        // Return the size of the board
        return BOARD_SIZE;
    }

    public TicTacFive(){

        mBoard = new char[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++)
            mBoard[i] = EMPTY_SPACE;

        mRand = new Random();
    }

    // Clear the board of all X's and O's
    public void clearBoard()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            mBoard[i] = EMPTY_SPACE;
        }
    }

    // set the given player at the given location on the game board.
    // the location must be available, or the board will not be changed.
    public void setMove(char player, int location)
    {
        mBoard[location] = player;
    }

    // Return the best move for the computer to make. You must call setMove()
    // to actually make the computer move to that location.
    public int getComputerMove()
    {
        int move;

        // First see if there's a move O can make to win
        for (int i = 0; i < getBOARD_SIZE(); i++)
        {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO)
            {
                char curr = mBoard[i];
                mBoard[i] = PLAYER_TWO;
                if (checkForWinner() == 5)
                {
                    setMove(PLAYER_TWO, i);
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < getBOARD_SIZE(); i++)
        {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO)
            {
                char curr = mBoard[i];
                mBoard[i] = PLAYER_ONE;
                if (checkForWinner() == 4)
                {
                    setMove(PLAYER_TWO, i);
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // Generate random move
        do
        {
            move = mRand.nextInt(getBOARD_SIZE());
        } while (mBoard[move] == PLAYER_ONE || mBoard[move] == PLAYER_TWO);

        setMove(PLAYER_TWO, move);
        return move;
    }

    // Look for who has won and return a status value confirming the winner.
    // Return 1 if it's a tie, 2 if X won, or 3
    // if O won.
    public int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 20; i +=5) {
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i+1] == PLAYER_ONE &&
                    mBoard[i+2] == PLAYER_ONE &&
                    mBoard[i+3] == PLAYER_ONE &&
                    mBoard[i+4] == PLAYER_ONE)
                return 2;

            if (mBoard[i] == PLAYER_TWO &&
                    mBoard[i+1] == PLAYER_TWO &&
                    mBoard[i+2] == PLAYER_TWO &&
                    mBoard[i+3] == PLAYER_TWO &&
                    mBoard[i+4] == PLAYER_TWO)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 4; i++) {
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i+5] == PLAYER_ONE &&
                    mBoard[i+10] == PLAYER_ONE &&
                    mBoard[i+15] == PLAYER_ONE &&
                    mBoard[i+20] == PLAYER_ONE)
                return 2;

            if (mBoard[i] == PLAYER_TWO &&
                    mBoard[i+5] == PLAYER_TWO &&
                    mBoard[i+10] == PLAYER_TWO &&
                    mBoard[i+15] == PLAYER_TWO &&
                    mBoard[i+20] == PLAYER_TWO)
                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == PLAYER_ONE &&
                mBoard[6] == PLAYER_ONE &&
                mBoard[12] == PLAYER_ONE &&
                mBoard[18] == PLAYER_ONE &&
                mBoard[24] == PLAYER_ONE) ||
                mBoard[4] == PLAYER_ONE &&
                        mBoard[8] == PLAYER_ONE &&
                        mBoard[12] == PLAYER_ONE &&
                        mBoard[16] == PLAYER_ONE &&
                        mBoard[20] == PLAYER_ONE)
            return 2;
        if ((mBoard[0] == PLAYER_TWO &&
                mBoard[6] == PLAYER_TWO &&
                mBoard[12] == PLAYER_TWO &&
                mBoard[18] == PLAYER_TWO &&
                mBoard[24] == PLAYER_TWO) ||
                mBoard[4] == PLAYER_TWO &&
                        mBoard[8] == PLAYER_TWO &&
                        mBoard[12] == PLAYER_TWO &&
                        mBoard[16] == PLAYER_TWO &&
                        mBoard[20] == PLAYER_TWO)
            return 3;

        // Check for a tie
        for (int i = 0; i < getBOARD_SIZE(); i++)
        {
            // if we find a number, then no one has won yet
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }
}
