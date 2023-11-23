package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.cards.HandCards

data class Hand(val handCards: HandCards) {
    fun addCard(card: Card) {
        handCards.add(card)
    }
    fun valueSum(): Int = handCards.cardList.sumOf { it.character.value }

    fun isBlackjack() = valueSum() == BLACK_JACK

    fun isBust() = valueSum() > BLACK_JACK

    override fun toString(): String = "$handCards - 결과 : ${valueSum()}"

    companion object {
        private const val BLACK_JACK = 21
    }
}
