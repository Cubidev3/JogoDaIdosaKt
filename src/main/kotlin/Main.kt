import game.GameBoard

fun main()
{
    val board = GameBoard()

    var playerId: Int = 0

    while (true)
    {
        println(board)
        println("Player $playerId, please type a coordinate to place an ${if (playerId == 0) 'X' else 'O'}")

        var x: Int
        var y: Int

        while (true)
        {
            try
            {
                x = readln().toInt()
                y = readln().toInt()

                when (playerId)
                {
                    0 -> board.placeX(x, y)
                    1 -> board.placeO(x, y)
                }

                break
            }
            catch (error: NumberFormatException)
            {
                println("Please input a number.")
                continue
            }
            catch (error: IllegalStateException)
            {
                println("Please input a valid position")
                continue
            }
        }

        if (board.didXWin())
        {
            println("Player 1 won")
            break
        }
        else if (board.didOWin())
        {
            println("Player 2 won")
            break
        }
        else if (board.isBoardFull())
        {
            println("Draw!")
            break
        }

        playerId = when (playerId)
        {
            0 -> 1
            else -> 0
        }
    }
}