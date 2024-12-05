package blackjack.domain

class BlackJackDealer(
    val blackJackPlayerCards: BlackJackPlayerCards,
) : BlackJackPlayer {
    override fun drawCard(blackJackDeck: BlackJackDeck): Boolean {
        var drawFlag = false
        while (blackJackPlayerCards.getCardsBestSum() in 1..DEALER_DRAW_THRESHOLD) {
            blackJackPlayerCards.addCard(blackJackDeck.draw())
            drawFlag = true
        }
        return drawFlag
    }

    override fun getBestSum(): Int {
        return blackJackPlayerCards.getCardsBestSum()
    }

    companion object {
        private const val DEALER_DRAW_THRESHOLD = 16
    }
}
