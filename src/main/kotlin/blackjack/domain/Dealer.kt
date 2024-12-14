package blackjack.domain

class Dealer {
    val hand: DealerCards = DealerCards()

    fun drawDefaultCards(
        deck: Deck,
        count: Int,
    ) {
        repeat(count) { addCard(deck.draw()) }
    }

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun shouldHit(): Boolean {
        return hand.getCardsSum() <= DEALER_CARDS_LIMIT_SIZE
    }

    fun isBlackJack(): Boolean {
        return hand.cards.size() == 2 && hand.getCardsSum() == Cards.GAME_LIMIT_NUMBER
    }

    fun isBust(): Boolean {
        return hand.getCardsSum() > Cards.GAME_LIMIT_NUMBER
    }

    fun getCardSum(): Int {
        return hand.getCardsSum()
    }

    companion object {
        private const val DEALER_CARDS_LIMIT_SIZE = 16
    }
}
