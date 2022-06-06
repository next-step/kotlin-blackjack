package blackjack.domain.rule

import blackjack.domain.PlayingCards

object ShuffleRule : PlayingCardsRule {
    override fun applyTo(playingCards: PlayingCards): PlayingCards {
        return PlayingCards.from(playingCards.shuffled())
    }
}
