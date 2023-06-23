package blackjack.domain

sealed interface CardNumber {
    val number: Int

    val points: List<Int>

    companion object {
        const val MIN_CARD_NUMBER = 1
        const val MAX_CARD_NUMBER = 13
        val NUMBER_RANGE = MIN_CARD_NUMBER..MAX_CARD_NUMBER
        private const val INVALID_CARD_NUMBER_ERROR_MESSAGE = "카드의 번호는 $MIN_CARD_NUMBER ~ $MAX_CARD_NUMBER 사이여야 합니다"

        fun of(value: Int): CardNumber {
            return when (value) {
                in AceCardNumber.NUMBER_RANGE -> AceCardNumber(value)
                in NumberCardNumber.NUMBER_RANGE -> NumberCardNumber(value)
                in JackQueenKingCardNumber.NUMBER_RANGE -> JackQueenKingCardNumber(value)
                else -> throw IllegalArgumentException(INVALID_CARD_NUMBER_ERROR_MESSAGE)
            }
        }
    }
}

data class AceCardNumber(override val number: Int) : CardNumber {
    override val points: List<Int> = listOf(11, 1)

    init {
        require(number in NUMBER_RANGE)
    }

    companion object {
        val NUMBER_RANGE = 1..1
    }
}

data class NumberCardNumber(override val number: Int) : CardNumber {
    override val points: List<Int> = listOf(number)

    init {
        require(number in NUMBER_RANGE)
    }

    companion object {
        val NUMBER_RANGE = AceCardNumber.NUMBER_RANGE.last + 1 until JackQueenKingCardNumber.NUMBER_RANGE.first
    }
}

data class JackQueenKingCardNumber(override val number: Int) : CardNumber {
    override val points: List<Int> = listOf(CARD_POINT)

    init {
        require(number in NUMBER_RANGE)
    }

    companion object {
        private const val CARD_POINT = 10
        const val JACK_NUMBER = 11
        const val QUEEN_NUMBER = 12
        const val KING_NUMBER = 13

        val NUMBER_RANGE = JACK_NUMBER..KING_NUMBER
    }
}
