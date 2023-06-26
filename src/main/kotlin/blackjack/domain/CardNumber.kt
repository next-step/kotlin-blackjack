package blackjack.domain

enum class CardNumber {

    ACE, TWO, TREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    companion object {
        const val MIN_CARD_NUMBER = 1
        const val MAX_CARD_NUMBER = 13
        val NUMBER_RANGE = MIN_CARD_NUMBER..MAX_CARD_NUMBER
        private const val INVALID_CARD_NUMBER_ERROR_MESSAGE = "카드의 번호는 $MIN_CARD_NUMBER ~ $MAX_CARD_NUMBER 사이여야 합니다"

        fun of(value: Int): CardNumber {
            return requireNotNull(values().find { it.ordinal + 1 == value }) { INVALID_CARD_NUMBER_ERROR_MESSAGE }
        }
    }
}
