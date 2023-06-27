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
            val (value, isAce) = calculateCardValue(card)
            totalValue += value
            numberOfAces += if (isAce) 1 else 0
        }

        return adjustForAces(totalValue, numberOfAces)
    }

    private fun calculateCardValue(card: Card): Pair<Int, Boolean> {
        return if (card.number == CardNumber.ACE) {
            Pair(11, true)
        } else {
            Pair(card.number.maxValue, false)
        }
    }

    private fun adjustForAces(totalValue: Int, numberOfAces: Int): Int {
        var adjustedValue = totalValue

        if (adjustedValue > 21 && numberOfAces > 0) {
            adjustedValue -= 10 * numberOfAces
        }

        return adjustedValue
    }
}
