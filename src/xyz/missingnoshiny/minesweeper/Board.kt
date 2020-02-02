package xyz.missingnoshiny.minesweeper

class Board(val width: Int, val height: Int, val bombs: Int) {

    val cells: List<List<Cell>> = generateCells();

    init {
        if (width < 1 || height < 1) throw IllegalArgumentException("The width and height need to be positive")
        if (bombs >= width * height) throw IllegalArgumentException("There needs to be less bombs than cells")
        generateValues()
    }

    private fun generateCells(): List<List<Cell>> {
        var tempCells = List(width * height) {i -> if (i < bombs) Cell(-1) else Cell(0)}.toCollection(ArrayList())
        tempCells.shuffle()
        return tempCells.chunked(width)
    }

    private fun generateValues() {
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (!cells[i][j].isBomb) cells[i][j].value = getNeighboringBombs(i, j);
            }
        }
    }

    fun getNeighboringBombs(x: Int, y: Int): Int {
        var count = 0
        for (i in (x - 1).coerceAtLeast(0) until (x + 2).coerceAtMost(width)) {
            for (j in (y - 1).coerceAtLeast(0) until (y + 2).coerceAtMost(height)) {
                if (cells[i][j].isBomb) count++
            }
        }
        return count
    }

    override fun toString(): String {
        var output = ""
        for (i in 0 until width) {
            var line = ""
            for (j in 0 until height) {
                line += if (cells[i][j].isBomb) "\uD83D\uDCA3" else "${cells[i][j].value} "
            }
            output += line + "\n"
        }
        return output
    }
}
