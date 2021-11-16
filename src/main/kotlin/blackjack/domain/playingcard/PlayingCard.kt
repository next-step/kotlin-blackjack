package blackjack.domain.playingcard

import blackjack.domain.Score

data class PlayingCard(private val suit: Suit, private val denomination: Denomination) {
    val score: Score
        get() = denomination.score

    fun hasAce(): Boolean = (denomination == Denomination.ACE)
}
