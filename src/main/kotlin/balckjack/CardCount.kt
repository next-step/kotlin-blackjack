package balckjack

sealed interface CardCount

data class SingleCount(val number: Int) : CardCount

data class DoubleCount(val minNumber: Int, val maxNumber: Int) : CardCount {
    fun sum(other: Int, limit: Int): Int {
        return if (limit >= maxNumber + other) {
            maxNumber + other
        } else {
            minNumber + other
        }
    }
}
