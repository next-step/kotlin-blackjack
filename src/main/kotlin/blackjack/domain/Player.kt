package blackjack.domain

class Player(val name: String) {
    private val deck = PlayerDeck()
    val cards: List<Card>
        get() = deck.cards

    fun takeCards(vararg cards: Card) {
        deck.addCards(*cards)
    }

    fun getTotalScore(): Int {
        return deck.getTotalScore()
    }
}
