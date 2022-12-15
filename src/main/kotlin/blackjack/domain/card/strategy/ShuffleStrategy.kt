package blackjack.domain.card.strategy

import blackjack.domain.card.PlayingCards

fun interface ShuffleStrategy {
    fun shuffle(): PlayingCards
}
