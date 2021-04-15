package blackjack

import blackjack.card.Card
import blackjack.card.CardValue

object BlackjackRule {

    private const val BLACKJACK_STANDARD_VALUE = 21
    private const val BLACKJACK_DEALER_STANDARD_VALUE = 16
    private const val ONE_CARD_OTHER_VALUE = 10

    fun getTotalSum(cards: List<Card>): Int {
        val sumValue = cards.sumBy { it.cardValue.value }

        if (!hasOneCard(cards)) return sumValue

        val sumIncludeOneCardOtherValue = sumOneCardOtherValue(cards)

        return if (sumIncludeOneCardOtherValue <= BLACKJACK_STANDARD_VALUE) sumIncludeOneCardOtherValue else sumValue
    }

    fun isReceiveMoreCard(totalSum: Int): Boolean {
        return totalSum < BLACKJACK_STANDARD_VALUE
    }

    fun isReceiveMoreDealerCard(totalSum: Int): Boolean {
        return totalSum <= BLACKJACK_DEALER_STANDARD_VALUE
    }

    fun resultWinnerAndLosers(players: List<Player>): List<BlackjackResult> {
        val winner: BlackjackResult? = players
            .filter { it.getTotalSum() <= BLACKJACK_STANDARD_VALUE }
            .maxBy { it.getTotalSum() }
            ?.let { BlackjackResult(it.name, true) }

        val losers = players.filter { it.name != winner?.name }.map { BlackjackResult(it.name, false) }

        return if (winner == null) losers else losers.plus(winner)
    }

    private fun hasOneCard(cards: List<Card>): Boolean {
        return cards.any { it.cardValue == CardValue.ONE }
    }

    private fun sumOneCardOtherValue(cards: List<Card>): Int {
        val sumValue = cards.filter { it.cardValue != CardValue.ONE }.sumBy { it.cardValue.value }
        return sumValue + ONE_CARD_OTHER_VALUE
    }
}
