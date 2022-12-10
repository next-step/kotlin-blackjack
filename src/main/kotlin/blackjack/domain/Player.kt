package blackjack.domain

data class Player(val name: String, var cards: Cards = Cards()) {
    fun getCards(card: Card) {
        cards = cards.add(card)
    }
}
