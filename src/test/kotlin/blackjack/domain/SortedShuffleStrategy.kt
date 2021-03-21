package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.ShuffleStrategy

class SortedShuffleStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.sortedWith(compareBy({ it.symbol }, { it.suit }))
    }
}
