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

    fun getCardSize(): Int {
        return cards.getCardSize()
    }

    fun isBurst(): Boolean {
        return cards.calculate() > MAXIMUM_SCORE
    }

    fun isBlackJack(): Boolean {
        return cards.calculate() == MAXIMUM_SCORE
    }

    companion object {
        private const val MAXIMUM_SCORE = 21
    }
}
