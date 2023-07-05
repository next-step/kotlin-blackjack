package blackjack.domain

class CardDeck {
    private val cards =
        Card.allCards
            .shuffled()
            .toMutableList()

    fun size(): Int = cards.size
    fun draw(): Card = cards.removeLast()
}
