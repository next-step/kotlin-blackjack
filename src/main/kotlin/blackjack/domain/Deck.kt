package blackjack.domain

class Deck(private val deck: Set<PlayingCard>) {

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
