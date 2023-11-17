package blackjack.business

class Dealer {
    private val _dealerCards: PlayerCards = PlayerCards(canDrawCardStrategy = DealerCanDrawCardStrategy())
    val cards: List<Card>
        get() = _dealerCards.cards

    fun addCard(card: Card) {
        _dealerCards.add(card)
    }

    fun canDrawCard(): Boolean {
        return _dealerCards.canDrawCard()
    }
}
