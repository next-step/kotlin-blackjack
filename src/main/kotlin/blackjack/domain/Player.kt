package blackjack.domain

class Player(val name: String) {
    private val deck = PlayerDeck()
    val cards: List<Card>
        get() = deck.cards

    fun takeCards(cards: List<Card>) {
        deck.addCards(cards)
    }

    fun takeCards(vararg cards: Card) {
        deck.addCards(cards.toList())
    }

    fun getTotalScore(): Int {
        return deck.getTotalScore()
    }
}
