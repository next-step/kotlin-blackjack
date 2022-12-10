package blackjack.domain

abstract class Participant(open val name: String, open val myCards: Cards = Cards()) {

    val totalScore: Int
        get() = calculate()

    fun receive(card: Card) = myCards.add(card)

    fun hasCard(card: Card): Boolean = myCards.hasCard(card)

    fun canDraw(): Boolean = totalScore < BLACK_JACK

    private fun calculate(): Int {
        val totalScore = myCards.getTotalScore()
        val hasAce = myCards.hasAce()
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
