package blackjack.domain

class Player(
    name: String,
    cards: Cards,
    burst: Boolean = false,
) : Participant(name, cards, burst) {
    override fun openedCards(): Cards = cards

    fun isWinner(dealerScore: Int): Boolean {
        if (burst) {
            return false
        }

        if (dealerScore > Cards.WINNING_NUMBER) {
            return true
        }

        val score = calculateScore()
        return score <= Cards.WINNING_NUMBER && score >= dealerScore
    }
}
