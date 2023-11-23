package blackjack.domain

enum class CardNumberInfo(val score: Int, private val mark: String? = null) {
    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    override fun toString(): String {
        return this.mark ?: this.score.toString()
    }

    companion object {
        const val ACE_UPPER_SCORE = 11
    }
}
