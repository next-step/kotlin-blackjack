package blackjack.domain.card.strategy

import blackjack.domain.card.PlayingCard

fun interface ShuffleStrategy {
    fun shuffle(): List<PlayingCard>
}
