package blackjack.domain.card

enum class CardNumber(val score: Int, private val number: Int = score) {

    ACE(1),
    TWO(2),
    TREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, 11),
    QUEEN(10, 12),
    KING(10, 13),
    ;

    companion object {
        const val MIN_CARD_NUMBER = 1
        const val MAX_CARD_NUMBER = 13
        val NUMBER_RANGE = MIN_CARD_NUMBER..MAX_CARD_NUMBER
        private const val INVALID_CARD_NUMBER_ERROR_MESSAGE = "카드의 번호는 $MIN_CARD_NUMBER ~ $MAX_CARD_NUMBER 사이여야 합니다"

        fun of(value: Int): CardNumber {
            return requireNotNull(values().find { it.number == value }) { INVALID_CARD_NUMBER_ERROR_MESSAGE }
        }
    }
}
