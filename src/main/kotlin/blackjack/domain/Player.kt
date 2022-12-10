package blackjack.domain

data class Player(val name: String, var cards: Cards = Cards()) {
    init {
        repeat(START_CARD_SIZE) {
            getCards(Deck.draw())
        }
    }
    fun getCards(card: Card) {
        cards = cards.add(card)
    }

    companion object {
        private const val START_CARD_SIZE = 2
    }
}
