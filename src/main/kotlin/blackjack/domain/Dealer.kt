package blackjack.domain

class Dealer {
    val hand: DealerCards = DealerCards()

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun shouldHit(): Boolean {
        return hand.getCardsSum() <= DEALER_CARDS_LIMIT_SIZE
    }

    companion object {
        private const val DEALER_CARDS_LIMIT_SIZE = 16
    }
}
