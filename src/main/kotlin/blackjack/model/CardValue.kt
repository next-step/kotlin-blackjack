package blackjack.model

enum class CardValue(private val score: Int) {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10),
    A(1);

    fun getName() = when(this){
        J -> "J"
        Q -> "Q"
        K -> "K"
        A -> "A"
        else -> score.toString()
    }

    fun getScore(total: Int = 0) = when (this) {
        A -> getAceScore(total)
        else -> score
    }

    companion object {
        private const val ACE_HIGH_SCORE = 11
        private const val ACE_LOW_SCORE = 1

        private fun getAceScore(total: Int): Int = if (total + CardValue.ACE_HIGH_SCORE > CardHand.BLACKJACK) {
            CardValue.ACE_LOW_SCORE
        } else {
            CardValue.ACE_HIGH_SCORE
        }
    }
}
