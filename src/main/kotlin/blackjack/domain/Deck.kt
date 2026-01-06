package blackjack.domain

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        cards.addAll(
            Suit.entries
                .flatMap { suit ->
                    Rank.entries.map { rank -> Card(suit, rank) }
                }.shuffled(),
        )
    }

    fun pop(): Card {
        if (cards.isEmpty()) throw NoSuchElementException("카드가 비어있습니다.")
        return cards.removeFirst()
    }
}
