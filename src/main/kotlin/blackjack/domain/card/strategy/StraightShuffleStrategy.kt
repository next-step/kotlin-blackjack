package blackjack.domain.card.strategy

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit

class StraightShuffleStrategy : ShuffleStrategy {
    override fun shuffle(): List<PlayingCard> {
        return Suit.values().flatMap { suit -> Denomination.values().map { denomination -> PlayingCard(suit, denomination) } }
    }
}
