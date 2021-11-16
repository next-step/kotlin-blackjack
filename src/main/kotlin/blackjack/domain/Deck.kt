package blackjack.domain

import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.playingcard.Suit
import blackjack.strategy.shuffle.DeckShuffleStrategy

@JvmInline
value class Deck private constructor(private val deck: List<PlayingCard>) {
    companion object {
        fun initialize(deckShuffleStrategy: DeckShuffleStrategy): Deck =
            Deck(deckShuffleStrategy.shuffle(playingCards()))

        private fun playingCards() = Suit.values()
            .map(Companion::playingCardPerSuit)
            .flatten()

        private fun playingCardPerSuit(suit: Suit) = Denomination.values()
            .map { denomination -> PlayingCard(suit, denomination) }
    }
}
