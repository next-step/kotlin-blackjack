package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Hand(val cards: Cards) {
    fun addCard(card: Card) {
        cards.add(card)
    }
    fun valueSum() : Int = cards.cardList.sumOf { it.character.value }

    fun isBlackjack() = valueSum() == blackjack

    fun isBust() = valueSum() > blackjack

    companion object {
        const val blackjack = 21
    }
}
