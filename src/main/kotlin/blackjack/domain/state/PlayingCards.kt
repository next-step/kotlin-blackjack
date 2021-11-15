package blackjack.domain.state

import blackjack.domain.PlayingCard

class PlayingCards private constructor(private val playingCard: Set<PlayingCard>) {
    companion object {
        fun initialize(): PlayingCards = PlayingCards(setOf())
    }
}
