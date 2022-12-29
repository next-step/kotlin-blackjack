package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class Player(
    val name: String,
    val money: Long = 0L,
    val cards: Cards = Cards()
) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        return cards.calculate()
    }

    fun isBurst(): Boolean {
        return cards.calculate() > MAXIMUM_SCORE
    }

    fun isBlackJack(): Boolean {
        return countCards() == BASIC_CARD_COUNT && isSameWithMaximumScore()
    }

    private fun isSameWithMaximumScore(): Boolean {
        return cards.calculate() == MAXIMUM_SCORE
    }

    private fun countCards(): Int {
        return cards.countCards()
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
        private const val MAXIMUM_SCORE = 21
    }
}
