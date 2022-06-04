package balckjack

sealed interface Score

data class SingleScore(val number: Int) : Score

data class DoubleScore(val minNumber: Int, val maxNumber: Int) : Score {
    fun sum(other: Int, limit: Int): Int {
        return if (limit >= maxNumber + other) {
            maxNumber + other
        } else {
            minNumber + other
        }
    }
}
