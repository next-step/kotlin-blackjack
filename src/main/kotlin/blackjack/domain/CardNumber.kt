package blackjack.domain

const val SPECIAL_CARD_SCORE = 10
const val ACE_CARD_MAX_SCORE = 11

enum class CardNumber(val value: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", SPECIAL_CARD_SCORE),
    QUEEN("Q", SPECIAL_CARD_SCORE),
    KING("K", SPECIAL_CARD_SCORE);

    companion object {
        fun of(value: String): CardNumber {
            return CardNumber.values().find { it.value == value }
                ?: throw IllegalArgumentException("잘못된 카드 번호입니다.")
        }
    }
}
