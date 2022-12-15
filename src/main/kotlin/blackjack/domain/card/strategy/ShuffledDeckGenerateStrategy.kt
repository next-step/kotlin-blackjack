package blackjack.domain.card.strategy

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit

class ShuffledDeckGenerateStrategy : DeckGenerateStrategy {
    override fun generate(): PlayingCards {
        return PlayingCards.of(
            Suit.values().flatMap { suit -> Denomination.values().map { denomination -> PlayingCard(suit, denomination) } }.shuffled()
        )
    }
}
