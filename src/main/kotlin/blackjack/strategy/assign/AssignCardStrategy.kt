package blackjack.strategy.assign

import blackjack.domain.Deck
import blackjack.domain.playingcard.PlayingCard

fun interface AssignCardStrategy {
    fun assign(deck: Deck): List<PlayingCard>
}