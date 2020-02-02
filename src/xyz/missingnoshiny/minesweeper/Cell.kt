package xyz.missingnoshiny.minesweeper

class Cell(var value: Int = 0) {
    val isBomb: Boolean
        get() = value == -1
    var isMarked: Boolean = false
    var isRevealed: Boolean = false
        private set

    fun reveal() {
        isRevealed = true;
    }
}