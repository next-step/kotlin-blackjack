package blackjack.domain.card

import blackjack.strategy.shuffle.DeckShuffleStrategy
import java.util.Stack

@JvmInline
value class Deck private constructor(private val deck: Stack<Card>) {

    fun pop(count: Int = DEFAULT_POP_COUNT): List<Card> = (DEFAULT_POP_COUNT..count).map { deck.pop() }

    companion object {
        private const val DEFAULT_POP_COUNT = 1

        fun initialize(deckShuffleStrategy: DeckShuffleStrategy): Deck =
            Deck(
                deckShuffleStrategy.shuffle(playingCardsAllSuit())
                    .toCollection(Stack())
            )

        private fun playingCardsAllSuit() = Suit.values()
            .flatMap(Companion::playingCardsPerSuit)

        private fun playingCardsPerSuit(suit: Suit): List<Card> = Denomination.values()
            .map { denomination -> Card(suit, denomination) }
    }
}
