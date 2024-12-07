package blackjack.domain

import blackjack.domain.Rank.ACE

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
            Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }
    }
}
