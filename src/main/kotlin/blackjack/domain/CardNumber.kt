package blackjack.domain

/**
 * 카드 번호 (A~K까지)
 */
enum class CardNumber(val text: String) {
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
    KING("K"),
    ;

    companion object {
        fun isAce(text: String): Boolean {
            return (ACE.text == text)
        }

        fun isRoyalFamily(text: String): Boolean {
            return (JACK.text == text || QUEEN.text == text || KING.text == text)
        }
    }
}
