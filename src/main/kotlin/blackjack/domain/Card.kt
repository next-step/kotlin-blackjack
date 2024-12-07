package blackjack.domain

import blackjack.domain.Rank.Companion.ACE

data class Card(
    val rank: Rank,
    val suit: Suit,
) {
    val score = rank.score

    fun isAce(): Boolean {
        return rank == ACE
    }

    companion object {
        val ALL: List<Card> =
            Suit.entries.flatMap { suit -> Rank.ALL.map { rank -> Card(rank, suit) } }
    }
}
