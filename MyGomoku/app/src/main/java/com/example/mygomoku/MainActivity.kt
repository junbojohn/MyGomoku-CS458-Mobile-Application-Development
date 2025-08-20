package com.example.mygomoku

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateDisplay()
    }

    // update the text that displays the game's data (disk count, who's turn it is, and is the game being played)
    fun updateDisplay() {
        val turnOrWin = findViewById<TextView>(R.id.turnAndWin)
        val playingOrNo = findViewById<TextView>(R.id.gameStatus)

        var game = findViewById<BoardManager>(R.id.boardManager)


        //check if the game is currently being played or not.
        if (game.gameStarted && !game.gameSet) {
            //if game is being played, display so that there is game currently going on
            playingOrNo.text = "Currently playing"

            //now, check who's turn is it. If 'whosTurn' is true, it is white's turn
            if (game.whosTurn) {
                turnOrWin.text = "Current turn: White"
            }

            //if 'whosTurn' is false, it is black's turn
            else {
                turnOrWin.text = "Current turn: Black"
            }
        }

        //check if game is finished. Once it is over, check who has the most disk
        //and announce the winner based on the result
        else if (!game.gameStarted && game.gameSet) {
            //if white disk made line of 5 disks first, white wins!
            if (game.whiteWin) {
                turnOrWin.text = "White Wins!"
            }

            //if black disk made line of 5 disks first, black wins!
            else if (game.blackWin) {
                turnOrWin.text = "Black Wins!"
            }

            //if neither players made line of 5 disks before board runs out of empty grid, it's a draw!
            else if (game.tie) {
                turnOrWin.text = "Draw!"
            }

            playingOrNo.text = "No game is on session"
        }

        //if there's no game currently going on, display no text on top and say no game is being played
        else {
            turnOrWin.text = ""
            playingOrNo.text = "No game is on session"
        }
    }

    //reset everything when new game button is pressed
    //1.) white and black disk count
    //2.) reset board to default
    //3.) reset 'validMoveMade' variable just to be safe
    fun newGame(view: View) {
        //grab the entity of the entire game itself
        var gameReset = findViewById<BoardManager>(R.id.boardManager)

        //set 'gameStarted' variable to true to indicate that the new game has commenced
        gameReset.gameStarted = true

        //set 'gameSet' variable to false so that game will properly run until it is over
        gameReset.gameSet = false

        //set 'whosTurn' variable to false so that black disk is the first one to start
        gameReset.whosTurn = false

        //set 3 of these boolean variables to false so that it can be re-used to determine who's the winner or if tie occurs
        gameReset.whiteWin = false
        gameReset.blackWin = false
        gameReset.tie = false

        //set 'validMoveMade' variable back to false to indicate that
        //no valid move has made yet to change player turn
        gameReset.validMoveMade = false

        //reset every grid of the board
        for (i in gameReset.grid.indices) {
            for (j in gameReset.grid[i].indices) {
                gameReset.grid[i][j] = 0
            }
        }

        updateDisplay()

        //invalidate: used to properly update both the board back-end and front-end
        gameReset.invalidate()
    }
}