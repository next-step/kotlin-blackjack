package blackjack

object Dealer {
    private val cards = CardDeck.makeAllCards().shuffled().toMutableList()

    fun popOneCard(): Card {
        return cards.removeLast()
    }
}
