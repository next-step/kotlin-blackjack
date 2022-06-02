package balckjack

sealed interface CardCount

data class SingleCount(val number: Int) : CardCount

data class DoubleCount(val minNumber: Int, val maxNumber: Int) : CardCount
