package blackjack.domain

import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.playingcard.Suit
import blackjack.strategy.shuffle.DeckShuffleStrategy
import java.util.Stack

data class Deck private constructor(private val _deck: List<PlayingCard>) {
    private val deck: Stack<PlayingCard> = _deck.toCollection(Stack())

    fun pop(count: Int = DEFAULT_POP_COUNT): List<PlayingCard> = (DEFAULT_POP_COUNT..count).map { deck.pop() }

    companion object {
        private const val DEFAULT_POP_COUNT = 1

        fun initialize(deckShuffleStrategy: DeckShuffleStrategy): Deck =
            Deck(deckShuffleStrategy.shuffle(playingCardsAllSuit()))

        private fun playingCardsAllSuit() = Suit.values()
            .flatMap(Companion::playingCardsPerSuit)

        private fun playingCardsPerSuit(suit: Suit) = Denomination.values()
            .map { denomination -> PlayingCard(suit, denomination) }
    }
}
