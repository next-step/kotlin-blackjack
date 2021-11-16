package blackjack.domain

import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.playingcard.Suit

@JvmInline
value class Deck private constructor(private val deck: List<PlayingCard>) {
    companion object {
        fun initialize(): Deck = Deck(
            Suit.values()
                .map(Companion::playingCardPerSuit)
                .flatten()
                .shuffled()
        )

        private fun playingCardPerSuit(suit: Suit) =
            Denomination.values().map { denomination -> PlayingCard(suit, denomination) }
    }
}
