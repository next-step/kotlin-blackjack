package blackjack.model.strategy

import blackjack.model.card.Card

class RandomStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
