package blackjack.domain.card

import blackjack.error.DuplicatePlayingCardException

@JvmInline
value class PlayingCards private constructor(private val playingCards: Set<PlayingCard>) {

    operator fun plus(playingCard: PlayingCard): PlayingCards =
        if (playingCards.contains(playingCard)) throw DuplicatePlayingCardException(playingCard)
        else from(playingCards.plus(playingCard))

    companion object {
        fun initialize(): PlayingCards = from(setOf())
        fun from(playingCards: Set<PlayingCard>): PlayingCards = PlayingCards(playingCards.toSet())
    }
}
