package blackjack.domain

class BlackJackDealer(
    name: String = "딜러",
    blackJackPlayerCards: BlackJackPlayerCards,
    profit: Int = 0,
) : BlackJackPlayer(name, blackJackPlayerCards, profit) {
    override fun drawCard(blackJackDeck: BlackJackDeck) {
        while (dealerDrawPossible()) {
            blackJackPlayerCards.addCard(blackJackDeck.draw())
        }
    }

    override fun drawPossible(): Boolean {
        return dealerDrawPossible()
    }

    private fun dealerDrawPossible() = blackJackPlayerCards.getCardsBestSum() in 1..DEALER_DRAW_THRESHOLD

    override fun getBestSum(): Int {
        return blackJackPlayerCards.getCardsBestSum()
    }

    fun win(bet: Int) {
        profit += bet
    }

    companion object {
        private const val DEALER_DRAW_THRESHOLD = 16
    }
}
