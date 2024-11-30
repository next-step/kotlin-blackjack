package blackjack.domain

import blackjack.domain.Rank.Companion.ACE

data class Card(private val rank: Rank, private val suit: Suit) {
    val score = rank.score

    fun isAce(): Boolean {
        return rank == ACE
    }
}
