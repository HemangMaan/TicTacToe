package com.hemangmaan.tictactoe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private var activePlayer = 1
    private var player1 = HashSet<Int>()
    private var player2 = HashSet<Int>()
    private var player1WinCount=0
    private var player2WinCount=0

    fun buClick(view: android.view.View) {
        val buSelected = view as Button

        var cellID = 0
        when(buSelected.id){
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }
        Log.d("cellID: ",cellID.toString())
        playGame(cellID,buSelected)
    }

    private fun playGame(cellId:Int, buSelected:Button){
        if(activePlayer ==1){
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.purple_200)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }else{
            buSelected.text = "0"
            buSelected.setBackgroundResource(R.color.teal_700)
            player2.add(cellId)
            activePlayer = 1
        }
        Log.d("button disabled",cellId.toString())
        buSelected.isEnabled = false
        checkWinner()
    }

    private fun autoPlay() {
        val emptyCell = ArrayList<Int>()
        for (cellID in 1..9){
            if(!player1.contains(cellID) && !player2.contains(cellID))
                emptyCell.add(cellID)
        }
        Log.d("Random slots",emptyCell.toString())
        var randInd = 0
        var cellId = 0
        if(emptyCell.size==0) {
            Toast.makeText(this,"Game is Draw",Toast.LENGTH_LONG).show()
            resetGame()
            return
        }
        else {
            randInd = Random().nextInt(emptyCell.size)
            cellId = emptyCell[randInd]
        }
        Log.d("Random index",cellId.toString())
        val buSelected:Button = when(cellId){
            1 -> findViewById(R.id.button1)
            2 -> findViewById(R.id.button2)
            3 -> findViewById(R.id.button3)
            4 -> findViewById(R.id.button4)
            5 -> findViewById(R.id.button5)
            6 -> findViewById(R.id.button6)
            7 -> findViewById(R.id.button7)
            8 -> findViewById(R.id.button8)
            9 -> findViewById(R.id.button9)
            else ->findViewById(R.id.button1)
        }
        playGame(cellId,buSelected)
    }

    private fun checkWinner() {
        var winner = -1
        //rows
        if((player1.contains(1) && player1.contains(2) && player1.contains(3)) || (player1.contains(4) && player1.contains(5) && player1.contains(6)) || (player1.contains(7) && player1.contains(8) && player1.contains(9))){
            winner =1
        }
        else if((player2.contains(1) && player2.contains(2) && player2.contains(3))||(player2.contains(4) && player2.contains(5) && player2.contains(6))||(player2.contains(7) && player2.contains(8) && player2.contains(9))){
            winner =2
        }
        //columns
        if((player1.contains(1) && player1.contains(4) && player1.contains(7)) || (player1.contains(2) && player1.contains(5) && player1.contains(8)) || (player1.contains(3) && player1.contains(6) && player1.contains(9))){
            winner =1
        }
        else if((player2.contains(1) && player2.contains(4) && player2.contains(7))||(player2.contains(2) && player2.contains(5) && player2.contains(8))||(player2.contains(3) && player2.contains(6) && player2.contains(9))){
            winner =2
        }
        //diagonals
        if((player1.contains(1) && player1.contains(5) && player1.contains(9)) || (player1.contains(3) && player1.contains(5) && player1.contains(7))){
            winner =1
        }
        else if((player2.contains(1) && player2.contains(5) && player2.contains(9))||(player2.contains(3) && player2.contains(5) && player2.contains(7))){
            winner =2
        }
        if(winner == 1){
            Toast.makeText(this,"Player 1 win the game",Toast.LENGTH_LONG).show()
            player1WinCount++
            resetGame()
        }else if(winner == 2){
            Toast.makeText(this,"Player 2 win the game",Toast.LENGTH_LONG).show()
            player2WinCount++
            resetGame()
        }
    }

    private fun resetGame() {
        player1.clear()
        player2.clear()
        activePlayer = 1
        for(i in 1..9){
            val buSelected:Button = when(i){
                1 -> findViewById(R.id.button1)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else ->findViewById(R.id.button1)
            }
            buSelected.text = ""
            buSelected.setBackgroundResource(R.color.white)
            buSelected.isEnabled = true
        }
        val player1Text = findViewById<TextView>(R.id.textView2)
        val player2Text = findViewById<TextView>(R.id.textView)
        player1Text.text = "Player 1: $player1WinCount"
        player2Text.text = "Player 2: $player2WinCount"
    }
}