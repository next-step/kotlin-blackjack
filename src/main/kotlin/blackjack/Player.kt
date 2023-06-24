package blackjack

class Player(
    name: String,
    cards: Cards,
) : Participant(name, cards) {
    override fun openedCards(): Cards = cards

    fun isWinner(dealerScore: Int): Boolean {
        val score = calculateScore()
        return score <= Cards.WINNING_NUMBER && score >= dealerScore
    }
}
