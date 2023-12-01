package blackjack.domain

@JvmInline
value class Deck(private val cards: MutableSet<Card>) {
    fun draw(): Card {
        check(cards.isNotEmpty()) { "no cards left" }
        val card = cards.random()
        cards.remove(card)
        return card
    }

    companion object {
        private val preparedDeck: Set<Card> =
            Suit.values().flatMap { suit ->
                Rank.values().map { rank ->
                    Card(suit = suit, rank = rank)
                }
            }.toSet()

        fun getDeck(): Deck = preparedDeck.toMutableSet().let(::Deck)
    }
}
