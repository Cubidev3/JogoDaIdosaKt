package game

import Board

class GameBoard()
{
    private val xId = 1
    private val oId = 2
    private val board = Board(3)

    fun placeX(x: Int, y: Int) { place(x, y, xId) }
    fun placeO(x: Int, y: Int) { place(x, y, oId) }

    private fun place(x: Int, y: Int, value: Int)
    {
        if (!board.isPositionEmpty(x, y))
            throw IllegalStateException("Position ($x, $y) is already occupied")

        board.place(x, y, value)
    }

    fun didXWin() : Boolean = didValueWin(xId)
    fun didOWin() : Boolean = didValueWin(oId)

    private fun didValueWin(value: Int): Boolean
    {
        val winArray = IntArray(3) { value }

        val wonWithLines = board.getLines().any { it.contentEquals(winArray) }
        val wonWithColumns = board.getColumns().any { it.contentEquals(winArray) }
        val wonWithDiagonals = board.getCrescentDiagonal().contentEquals(winArray) || board.getDecreasingDiagonal().contentEquals(winArray)

        return wonWithLines || wonWithColumns || wonWithDiagonals
    }
    
    fun isValidPosition(pos: Int): Boolean = board.isValidPosition(pos)

    fun isBoardFull(): Boolean = board.isBoardFull()
    fun isBoardEmpty(): Boolean = board.isBoardEmpty()

    override fun toString(): String
    {
        return board.getLines().joinToString("\n") { line ->
            line.joinToString(" | ") { id ->
                when (id) {
                    xId -> "X"
                    oId -> "O"
                    else -> " "
                }
            }
        }
    }
}