package blackjack.domain.player

import blackjack.domain.playingcard.PlayingCard
import blackjack.error.DuplicatePlayingCardException

@JvmInline
value class PlayingCards private constructor(private val playingCards: Set<PlayingCard>) {

    fun addCards(extraPlayingCards: List<PlayingCard>): PlayingCards =
        if (playingCards.any(extraPlayingCards::contains)) throw DuplicatePlayingCardException(extraPlayingCards)
        else PlayingCards(playingCards.plus(extraPlayingCards))

    companion object {
        fun initialize(): PlayingCards = from(setOf())
        private fun from(playingCards: Set<PlayingCard>): PlayingCards = PlayingCards(playingCards.toSet())
    }
}
