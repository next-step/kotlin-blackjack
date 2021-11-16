package blackjack.strategy.assign

import blackjack.domain.Deck
import blackjack.domain.playingcard.PlayingCard

object FirstAssignCardStrategy : AssignCardStrategy {
    private const val TWO = 2
    override fun assign(deck: Deck): List<PlayingCard> = deck.pop(TWO)
}
