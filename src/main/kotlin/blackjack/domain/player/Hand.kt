package blackjack.domain.player

import blackjack.domain.card.HandCards
import blackjack.model.Card

data class Hand(val handCards: HandCards) {
    fun addCard(card: Card) {
        handCards.add(card)
    }
    fun valueSum(): Int = handCards.cardList.sumOf { it.character.value }

    fun isBlackjack() = valueSum() == blackjack

    fun isBust() = valueSum() > blackjack

    override fun toString(): String = "$handCards - 결과 : ${valueSum()}"

    companion object {
        const val blackjack = 21
    }
}
