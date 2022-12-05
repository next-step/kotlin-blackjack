package blackjack.domain

enum class CardNumber(val value: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    companion object {
        fun of(value: String): CardNumber {
            return CardNumber.values().find { it.value == value }
                ?: throw IllegalArgumentException("잘못된 카드 번호입니다.")
        }
    }
}
