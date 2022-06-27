package blackjack.entity

import blackjack.entity.CardNumber.Companion.ACE_CARD_OF_TEN

class Wallet(val cards: List<Card>) {
    val sumUp: Int = cards.sumOf { card: Card -> checkSumUpCondition(card.number) }

    fun checkSumUpCondition(number: CardNumber): Int{
        if(number == CardNumber.ACE && this.sumUp <=11 ){
            return ACE_CARD_OF_TEN
        }
        return number.value
    }

    fun isAbleToDraw(limit: Int): Boolean {
        return (this.sumUp < limit)
    }

    fun getMutableCardList(): MutableList<Card> {
        return this.cards.toMutableList()
    }
}
