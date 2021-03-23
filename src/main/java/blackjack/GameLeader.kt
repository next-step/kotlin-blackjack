package blackjack

import blackjack.card.Card

class GameLeader(cards: List<Card>) {

    companion object {
        private const val BLACKJACK_STANDARD_VALUE = 21
    }

    private val _cards: MutableList<Card> = cards.toMutableList()

    fun giveTwoCards(): List<Card> {
        return (0..1).map { giveCard() }
    }

    fun giveCard(): Card {
        _cards.shuffle()
        return _cards.removeAt(0)
    }

    fun sum(cards: List<Card>): Int {
        val sumValue = cards.sumBy { it.cardValue.otherValue }
        return if (sumValue > BLACKJACK_STANDARD_VALUE) cards.sumBy { it.cardValue.value } else sumValue
    }
}
