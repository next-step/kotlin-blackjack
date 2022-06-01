package blackjack.domain

interface Deck {
    fun draw(): Card
}

class ShuffledDeck : Deck {
    private val cards: MutableList<Card> = Card.entireCards
        .shuffled()
        .toMutableList()

    override fun draw(): Card {
        check(cards.isNotEmpty())
        return cards.removeFirst()
    }
}
