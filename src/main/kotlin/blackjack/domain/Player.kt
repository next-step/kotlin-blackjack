package blackjack.domain

import blackjack.domain.GameRule.BLACKJACK_SCORE

class Player(
    val name: String = "",
    cards: List<Card> = emptyList()
) {

    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    val canReceive: Boolean
        get() = score() < BLACKJACK_SCORE

    fun addCard(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    fun addCard(newCards: Card) {
        _cards.add(newCards)
    }


    private fun aceScore(total: Int): Int {
        return if (total + (Denomination.ACE.extraScore) <= GameRule.BLACKJACK_SCORE)
            Denomination.ACE.extraScore
        else Denomination.ACE.score
    }

    fun score(): Int {
        val hasAce = cards.firstOrNull() { it.denomination == Denomination.ACE }

        val denominations = cards
            .filter { it.denomination.isSingleScore }
            .map { it.denomination }

        val total = denominations.sumOf {
            it.score
        }

        return if (hasAce != null) {
            total + aceScore(total)
        } else total
    }
}
