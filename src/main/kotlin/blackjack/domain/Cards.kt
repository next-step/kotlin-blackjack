package blackjack.domain

import blackjack.domain.Rank.Companion.ACE

data class Cards(val cards: List<Card>) {
    fun calculateScore(): Int {
        return cards.filterNot { it.rank == ACE }
            .sumOf { it.rank.score }
    }
}
