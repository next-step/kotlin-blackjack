package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.model.Card

data class Hand(val cards: Cards) {
    fun addCard(card: Card) {
        cards.add(card)
    }
    fun valueSum(): Int = cards.cardList.sumOf { it.character.value }

    fun isBlackjack() = valueSum() == blackjack

    fun isBust() = valueSum() > blackjack

    override fun toString(): String = "$cards - 결과 : ${valueSum()}"

    companion object {
        const val blackjack = 21
    }
}
