package blackjack.domain.card

enum class Denomination(
    val title: String,
    private val score: Int
) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    fun getScore(): Int {
        return score
    }

    fun getScore(accumulatedScore: Int): Int {
        if (this == ACE) {
            return calculateAceScore(accumulatedScore)
        }
        return score
    }

    private fun calculateAceScore(accumulatedScore: Int): Int {
        if (accumulatedScore + score + ACE_EXCEPTION_NUMBER > WINNING_NUMBER) {
            return score
        }
        return score + ACE_EXCEPTION_NUMBER
    }

    companion object {
        const val ACE_EXCEPTION_NUMBER = 10
        const val WINNING_NUMBER = 21
    }
}
