package blackjack.domain.card.strategy

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit

class RandomShuffleStrategy : ShuffleStrategy {
    override fun shuffle(): PlayingCards {
        return PlayingCards.of(
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> PlayingCard(suit, denomination) } }.shuffled()
        )
    }
}
