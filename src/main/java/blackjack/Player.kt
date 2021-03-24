package blackjack

import blackjack.card.Card
import blackjack.const.BlackjackConst.BLACKJACK_STANDARD_VALUE

class Player(val name: String, cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalSum(): Int {
        val sumValue = _cards.sumBy { it.cardValue.value }
        val sumOtherValue = _cards.sumBy { it.cardValue.otherValue }

        if (!hasOtherValueCard()) return sumValue

        return if (sumOtherValue <= BLACKJACK_STANDARD_VALUE) sumOtherValue else sumValue
    }

    fun isReceiveMoreCard(): Boolean {
        return getTotalSum() < BLACKJACK_STANDARD_VALUE
    }

    private fun hasOtherValueCard(): Boolean {
        return _cards.any { it.cardValue.hasOtherValue }
    }
}
