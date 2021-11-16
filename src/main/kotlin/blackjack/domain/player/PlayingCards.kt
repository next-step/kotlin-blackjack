package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.playingcard.PlayingCard
import blackjack.error.DuplicatePlayingCardException

@JvmInline
value class PlayingCards private constructor(private val playingCards: Set<PlayingCard>) {

    operator fun plus(extraPlayingCards: List<PlayingCard>): PlayingCards =
        if (playingCards.any(extraPlayingCards::contains)) throw DuplicatePlayingCardException(extraPlayingCards)
        else PlayingCards(playingCards.plus(extraPlayingCards))

    fun sumScore(): Score {
        val sum = playingCards
            .map(PlayingCard::score)
            .reduce(Score::plus)
        if (playingCards.any(PlayingCard::hasAce) && sum.canAdditionalAceScore()) {
            return sum.plus(Score.additionalAceScore())
        }
        return sum
    }

    companion object {
        fun initialize(): PlayingCards = from(setOf())
        fun from(playingCards: Set<PlayingCard>): PlayingCards = PlayingCards(playingCards.toSet())
    }
}
