package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber

class Hand(
    private val cardList: ArrayList<Card> = ArrayList()
) {
    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun size(): Int {
        return cardList.size
    }

    fun displayCards(): String {
        return cardList.joinToString(", ") { it.toString() }
    }

    fun getTotalValue(): Int {
        var totalValue = 0
        var numberOfAces = 0

        for (card in cardList) {
            totalValue += getCardValue(card)
            if (card.number == CardNumber.ACE) {
                numberOfAces++
            }
        }

        while (totalValue > 21 && numberOfAces > 0) {
            totalValue -= 10
            numberOfAces--
        }

        return totalValue
    }
    private fun getCardValue(card: Card): Int {
        return if (card.number == CardNumber.ACE) {
            11
        } else {
            card.number.maxValue
        }
    }
}
