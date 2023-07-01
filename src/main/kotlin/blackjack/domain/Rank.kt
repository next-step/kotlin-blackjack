package blackjack.domain

sealed class Rank(val symbol: String) {
    object King : Rank("K")
    object Queen : Rank("Q")
    object Jack : Rank("J")
    object Ace : Rank("A")
    class Number(val number: Int = (NUMBER_MIN..NUMBER_MAX).random()) : Rank(number.toString()) {
        init {
            require(number in NUMBER_MIN..NUMBER_MAX) { "Number 는 1-9 사이의 숫자만 가능합니다. [입력: $number]" }
        }
    }

    companion object {
        const val NUMBER_MIN = 1
        const val NUMBER_MAX = 9
        fun random(): Rank {
            return listOf(King, Queen, Jack, Ace, Number())
                .shuffled()
                .first()
        }
    }
}