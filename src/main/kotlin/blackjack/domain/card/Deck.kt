package blackjack.domain.card

import blackjack.strategy.shuffle.DeckShuffleStrategy

@JvmInline
value class Deck private constructor(private val deck: ArrayDeque<Card>) {

    fun pop(count: Int = DEFAULT_POP_COUNT): List<Card> = (DEFAULT_POP_COUNT..count).map { deck.removeLast() }

    companion object {
        private const val DEFAULT_POP_COUNT = 1

        fun initialize(deckShuffleStrategy: DeckShuffleStrategy): Deck =
            Deck(
                deckShuffleStrategy.shuffle(playingCardsAllSuit())
                    .toCollection(ArrayDeque())
            )

        private fun playingCardsAllSuit() = Suit.values()
            .flatMap(::playingCardsPerSuit)

        private fun playingCardsPerSuit(suit: Suit): List<Card> = Denomination.values()
            .map { denomination -> Card(suit, denomination) }
    }
}
