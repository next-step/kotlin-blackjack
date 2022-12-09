package blackjack.domain

abstract class Participant(open val name: String, open val cards: Cards = Cards()) {

    val totalScore: Int
        get() = calculate()

    fun addCard(card: Card) = cards.addCard(card)

    fun hasCard(card: Card): Boolean = cards.hasCard(card)

    fun isScoreExceedOrSame(): Boolean = totalScore >= BLACK_JACK

    private fun calculate(): Int {
        val totalScore = cards.getTotalScore()
        val hasAce = cards.hasAce()
        return if (totalScore <= BONUS_ACE_SCORE && hasAce) {
            totalScore + 10
        } else {
            totalScore
        }
    }

    companion object {
        private const val BLACK_JACK = 21
        private const val BONUS_ACE_SCORE = 11
    }
}
