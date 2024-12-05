package blackjack.entity

import blackjack.entity.GameRules.ACE_EXTRA_VALUE
import blackjack.entity.GameRules.MAX_SCORE

class Hand(cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card> get() = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBusted(): Boolean {
        return calculateScore() > MAX_SCORE
    }

    fun isBlackjack(): Boolean {
        return cards.size == 2 &&
            cards.any { it.rank == Rank.ACE } &&
            cards.any { it.rank.value == 10 }
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
