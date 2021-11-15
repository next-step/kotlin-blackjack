package blackjack.domain

@JvmInline
value class Deck private constructor(private val _deck: Set<PlayingCard>) {
    val deck: List<PlayingCard>
        get() = _deck.shuffled()

    companion object {
        fun initialize(): Deck = Deck(
            Suit.values()
                .flatMap(suitToDenominations())
                .map { PlayingCard(it.first, it.second) }
                .toSet()
        )

        private fun suitToDenominations() = { suit: Suit -> Denomination.values().map(suitToDenomination(suit)) }

        private fun suitToDenomination(suit: Suit) = { denomination: Denomination -> suit to denomination }
    }
}
