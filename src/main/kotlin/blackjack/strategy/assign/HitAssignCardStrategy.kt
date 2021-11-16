package blackjack.strategy.assign

import blackjack.domain.Deck
import blackjack.domain.playingcard.PlayingCard

object HitAssignCardStrategy : AssignCardStrategy {
    override fun assign(deck: Deck): List<PlayingCard> = deck.pop()
}
