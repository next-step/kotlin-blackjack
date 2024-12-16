package blackjack

import blackjack.domain.Card
import blackjack.domain.Ranks

class UserCards(private val cards: MutableList<Card>) : Collection<Card> by cards {
    fun calculatePoints(): Int {
        val basePoints = cards.sumOf { it.rank.points[0] }
        val hasAce = cards.any { it.rank == Ranks.ACE }
        if (hasAce && basePoints + 10 <= 21) {
            return basePoints + 10
        }
        return basePoints
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
