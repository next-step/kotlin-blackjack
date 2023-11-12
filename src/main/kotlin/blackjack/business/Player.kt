package blackjack.business

class Player(val name: String, cards: List<Card> = listOf()) {
    private val _cards: PlayerCards = PlayerCards(cards)
    val cards: List<Card>
        get() = _cards.cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun canDrawCard(): Boolean {
        return _cards.canDrawCard()
    }
}
