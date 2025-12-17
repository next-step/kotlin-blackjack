package blackjack.domain

class Deck {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = _cards

    init {
        Suit.entries
            .flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }.shuffled()
            .toCollection(_cards)
    }

    fun pop(): Card = _cards.pop()

    private fun MutableList<Card>.pop(): Card {
        val firstCard = first()
        remove(firstCard)
        return firstCard
    }
}
