package blackjack.player

import blackjack.card.Card
import blackjack.card.CardSymbol

object BlackjackScoreCalculator {
    private const val ACE_DIVISION_VALUE = 11
    private const val ACE_ADDITIONAL_VALUE = 10

    fun getScore(cards: List<Card>): Int {
        val aces = cards.filter { it.symbol == CardSymbol.ACE }

        var score = cards.sumOf { it.symbol.value }

        if (score <= ACE_DIVISION_VALUE && aces.isNotEmpty()) {
            score += ACE_ADDITIONAL_VALUE
        }
        return score
    }
}
