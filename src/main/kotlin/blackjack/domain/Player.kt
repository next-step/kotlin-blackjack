package blackjack.domain

data class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards

    fun draw(cardDeck: CardDeck) {
        _cards.add(cardDeck.pop())
    }

    fun cardCount() = _cards.size
}
