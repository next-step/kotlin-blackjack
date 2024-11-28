package blackjack.entity

import blackjack.entity.GameRules.ACE_EXTRA_VALUE
import blackjack.entity.GameRules.MAX_SCORE

class Hand(val cards: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun describe(): String {
        return cards.joinToString { it.describe() }
    }

    fun calculateScore(): Int {
        val total = calculateTotal()
        val aceCount = countAces()

        return if (canApplyAceBonus(aceCount, total)) {
            total + ACE_EXTRA_VALUE
        } else {
            total
        }
    }

    private fun canApplyAceBonus(
        aceCount: Int,
        total: Int,
    ) = hasAce(aceCount) && isAceBonusApplicable(total)

    private fun isAceBonusApplicable(total: Int) = total + ACE_EXTRA_VALUE <= MAX_SCORE

    private fun hasAce(aceCount: Int) = aceCount > 0

    private fun countAces() = cards.count { it.rank == Rank.ACE }

    private fun calculateTotal() = cards.sumOf { it.rank.value }
}
