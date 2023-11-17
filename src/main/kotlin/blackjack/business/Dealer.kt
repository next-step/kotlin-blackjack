package blackjack.business

class Dealer {
    private val _dealerCards: PlayerCards = PlayerCards()
    val cards: List<Card>
        get() = _dealerCards.cards

    fun addCard(card: Card) {
        _dealerCards.add(card)
    }
}
