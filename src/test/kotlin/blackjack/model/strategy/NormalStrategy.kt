package blackjack.model.strategy

import blackjack.model.card.Card

class NormalStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards
    }
}
