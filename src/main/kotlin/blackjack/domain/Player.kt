package blackjack.domain

class Player(val name: Name) {
    private val _cards: Cards = Cards(mutableListOf())
    val cards = _cards

    fun receiveCard(card: Card) {
        _cards.add(card)
    }
}
