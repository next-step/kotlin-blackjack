package blackjack.domain

class Deck {
    val cards: MutableList<Card> = Suit.values().flatMap { suit ->
        Denomination.values().map { Card(suit, it) }
    }.shuffled().toMutableList()

    fun drawCard(): Card {
        return cards.removeAt(0)
    }
}
