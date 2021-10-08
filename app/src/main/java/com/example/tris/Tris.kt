package com.example.tris

import java.lang.IllegalStateException

class Tris {
    val grid = mutableListOf(
        mutableListOf(0,0,0),
        mutableListOf(0,0,0),
        mutableListOf(0,0,0)
    )

    fun play(player: Int, row: Int, column: Int): Int {
        if(grid[row][column] != 0) throw IllegalStateException()
        grid[row][column] = player
        if(checkWin(player, row, column)) {
            return player
        } else {
            return 0
        }
    }

    private fun checkWin(player: Int, row: Int, column: Int): Boolean {
        if(checkColumn(player, column)) return true
        if(checkRow(player, row)) return true
        if(checkDiagonal(player, row, column)) return true
        return false
    }

    private fun checkDiagonal(player: Int, row: Int, column: Int): Boolean {
        var victory = false
        if(row - column == 0) {
            victory = true
            for(i in 0 until 3) {
                if(grid[i][i] != player) victory = false
            }
        }
        if(!victory && row + column == 2) {
            victory = true
            for(i in 0 until 3) {
                if(grid[i][2-i] != player) victory = false
            }
        }
        return victory
    }

    private fun checkColumn(player: Int, column: Int): Boolean {
        var victory = true
        for(i in 0 until 3) {
            if(grid[i][column] != player) victory = false
        }
        return victory
    }

    private fun checkRow(player: Int, row: Int): Boolean {
        var victory = true
        for(i in 0 until 3) {
            if(grid[row][i] != player) victory = false
        }
        return victory
    }
}