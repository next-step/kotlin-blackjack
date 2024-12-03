package blackjack.domain

class BlackJackDealer(
    name: String = "딜러",
    blackJackPlayerCards: BlackJackPlayerCards,
) : BlackJackPlayer(name, blackJackPlayerCards) {
    override fun isDrawPossible(): Boolean {
        return blackJackPlayerCards.getCardsBestSum() in 1..DEALER_DRAW_THRESHOLD
    }

    companion object {
        private const val DEALER_DRAW_THRESHOLD = 16
    }
}
