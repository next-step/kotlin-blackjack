package blackjack.domain

class Player(
    override val name: String,
) : Participant() {
    override val cards: Cards = Cards()

    fun canReceiveCard(isHit: Boolean): Boolean {
        return !isBust() && isHit
    }

    fun getResult(): PlayerResult {
        if (isBust()) {
            return PlayerResult.LOSE
        }
        if (Dealer.isBust()) {
            return PlayerResult.WIN
        }

        val playerScore = cards.calculateScore()
        val dealerScore = Dealer.cards.calculateScore()

        return when {
            playerScore > dealerScore -> PlayerResult.WIN
            playerScore == dealerScore -> PlayerResult.DRAW
            else -> PlayerResult.LOSE
        }
    }
}
