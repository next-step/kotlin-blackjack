package blackjack.entity

import blackjack.entity.CardNumber.Companion.ACE_CARD_OF_ELEVEN

class Hands(val cards: List<Card>) {
    val sumUp: Int = cards.sumOf { card: Card -> checkSumUpCondition(card.number) }

    fun checkSumUpCondition(number: CardNumber): Int {
        if (number == CardNumber.ACE && this.sumUp <= 11) {
            return ACE_CARD_OF_ELEVEN
        }
        return number.value
    }

    fun isAbleToDraw(limit: Int): Boolean {
        return (this.sumUp < limit)
    }

    fun addNewCard(): Hands {
        val cards = this.cards.toMutableList()
        cards.add(CardDrawer.drawSingleCard())
        return Hands(cards)
    }
}
