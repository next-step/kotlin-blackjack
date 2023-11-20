package blackjack.model.strategy

import blackjack.model.card.Card

interface ShuffleStrategy {
    fun shuffle(): List<Card>
}
