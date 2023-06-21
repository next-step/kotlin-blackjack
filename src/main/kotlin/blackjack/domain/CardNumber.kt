package blackjack.domain

sealed interface CardNumber {
    val number: Int

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

    companion object {
        val NUMBER_RANGE = 1..1
    }
}

data class NumberCardNumber(override val number: Int) : CardNumber {

    companion object {
        val NUMBER_RANGE = 2..10
    }
}

data class JackQueenKingCardNumber(override val number: Int) : CardNumber {

    companion object {
        val NUMBER_RANGE = 11..CardNumber.MAX_CARD_NUMBER
    }
}
