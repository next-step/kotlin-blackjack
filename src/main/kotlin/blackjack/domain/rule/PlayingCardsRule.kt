package blackjack.domain.rule

import blackjack.domain.PlayingCards

interface PlayingCardsRule {
    fun applyTo(playingCards: PlayingCards): PlayingCards
}
