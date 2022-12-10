package blackjack

data class Player(val name: String, val cards: Cards = Cards()) {
    fun getCards(card: Card): Player {
        val addedCards = cards.add(card)
        return this.copy(cards = addedCards)
    }
}
