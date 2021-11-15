package blackjack.domain.state

import blackjack.domain.PlayingCard

@JvmInline
value class PlayingCards private constructor(private val playingCards: Set<PlayingCard>) {

    operator fun plus(playingCard: PlayingCard): PlayingCards = from(playingCards.plus(playingCard))

    companion object {
        fun initialize(): PlayingCards = from(setOf())
        fun from(playingCards: Set<PlayingCard>): PlayingCards = PlayingCards(playingCards.toSet())
    }
}
