package blackjack.domain

class Player(val name: Name) {
    private val _cards: Cards = Cards(mutableSetOf())
    val cards = _cards

    fun receiveCard(card: Card) {
        _cards.add(card)
    }
}
