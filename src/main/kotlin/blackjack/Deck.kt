package blackjack

class Deck(cards: List<Card> = Card.DECK) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()
    fun pick(): Card {
        val shuffledCards = _cards.shuffled()
        val card = shuffledCards.toMutableList().removeFirst()
        _cards.remove(card)
        return card
    }
}
