package blackjack.domain

class Player(
    override val name: String,
) : Participant() {
    override val cards: Cards = Cards()

    fun canReceiveCard(isHit: Boolean): Boolean {
        return !isBust() && isHit
    }

    fun getResult(dealer: Dealer): PlayerResult {
        if (isBust()) {
            return PlayerResult.LOSE
        }
        if (dealer.isBust()) {
            return PlayerResult.WIN
        }

        val playerScore = cards.calculateScore()
        val dealerScore = dealer.cards.calculateScore()

        return when {
            playerScore > dealerScore -> PlayerResult.WIN
            playerScore == dealerScore -> PlayerResult.DRAW
            else -> PlayerResult.LOSE
        }
    }
}
