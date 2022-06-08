package blackjack.domain.rule

import blackjack.domain.PlayingCards

object DistinctRule : PlayingCardsRule {
    override fun applyTo(playingCards: PlayingCards): PlayingCards {
        return PlayingCards.from(playingCards.distinct())
    }
}
