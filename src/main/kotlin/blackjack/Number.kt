package blackjack

enum class Number(
    val text: String
) {
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

    fun isAce(): Boolean {
        return this == ACE
    }

    fun value(): Int {
        return when(this) {
            ACE -> 1
            KING, QUEEN, JACK -> 10
            else -> text.toInt()
        }
    }

    companion object {
        fun findByText(text: String): Number {
            return values().first { it.text == text }
        }
    }
}
