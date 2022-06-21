package blackjack.domain.cardrule

import blackjack.domain.PlayingCards

interface PlayingCardsRule {
    fun applyTo(playingCards: PlayingCards): PlayingCards
}
