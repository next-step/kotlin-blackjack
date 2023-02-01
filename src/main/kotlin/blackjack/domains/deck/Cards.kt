package blackjack.domains.deck

import blackjack.domains.GameRule
import blackjack.ScoreCalculator.sumOfNumbers

class Cards(cards: List<Card> = listOf()) {
    val values: MutableList<Card> = cards.toMutableList()

    override fun toString(): String {
        return values.joinToString { it.toString() }
    }

    fun addCard(card: Card) {
        values.add(card)
    }

    fun isDrawable(condition: Int): Boolean {
        return values.sumOf { it.pokerNumber.number } < condition
    }

    fun hasAce(): Boolean {
        return values.singleOrNull { it.pokerNumber.isAce() }?.let { true } ?: false
    }

    fun sumOfCards(): Int {
        return sumOfNumbers(this, GameRule.BLACKJACK)
    }

    fun getCardSize(): Int {
        return this.values.size
    }

    fun isBlackJack(): Boolean {
        return this.sumOfCards() == GameRule.BLACKJACK
    }
}
