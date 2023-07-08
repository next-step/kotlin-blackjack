package blackjack.domain

class Deck {
    private val _cards: MutableList<Card> = Suit.values().flatMap { suit ->
        Denomination.values().map { Card(suit, it) }
    }.shuffled().toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun drawCard(): Card {
        return _cards.removeAt(0)
    }
}
