package blackjack.domain.rule

import blackjack.domain.PlayingCards

interface PlayingCardsRule {
    fun apply(playingCards: PlayingCards): PlayingCards
}
