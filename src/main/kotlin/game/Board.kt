class Board(private val size: Int = 3, private val emptyId: Int = 0)
{
    private val board = Array(size) { IntArray(size) { emptyId } }

    fun place(x: Int, y: Int, value: Int)
    {
        if (!isValidPosition(x) || !isValidPosition(y))
            throw IllegalStateException("Cannot place value in position ($x, $y)")

        board[y][x] = value
    }

    fun remove(x: Int, y: Int)
    {
        if (!isValidPosition(x) || !isValidPosition(y))
            throw IllegalStateException("Cannot remove value in position ($x, $y)")

        board[y][x] = emptyId
    }

    fun replace(x: Int, y: Int, value: Int)
    {
        remove(x, y)
        place(x, y, value)
    }

    fun getElement(x: Int, y: Int) : Int
    {
        if (!isValidPosition(x) || !isValidPosition(y))
            throw IllegalStateException("Cannot place value in position ($x, $y)")

        return board[y][x]
    }

    fun getLine(y: Int): IntArray
    {
        if (!isValidPosition(y))
            throw IllegalStateException("Cannot get line in index $y")

        return board[y]
    }

    fun getColumn(x: Int): IntArray
    {
        if (!isValidPosition(x))
            throw IllegalStateException("Cannot get column in index $x")

        return IntArray(size) { y -> getElement(x, y) }
    }

    fun getDecreasingDiagonal() : IntArray
    {
        return IntArray(size) { pos -> getElement(pos, pos)}
    }

    fun getCrescentDiagonal() : IntArray
    {
        return IntArray(size) { pos -> getElement(size - 1 - pos, size - 1 - pos) }
    }

    fun getLines(): Array<IntArray> = board
    fun getColumns(): Array<IntArray> = Array(3) { getColumn(it) }

    fun isValidPosition(position: Int): Boolean = position in 0..<size

    fun isPositionEmpty(x: Int, y: Int): Boolean = getElement(x, y) == emptyId

    fun isBoardEmpty(): Boolean = board.all { line -> line.all { element -> element == emptyId } }

    fun isBoardFull(): Boolean = board.all { line -> line.all { element -> element != emptyId } }

    override fun toString(): String
    {
        return board.joinToString("\n")
    }
}