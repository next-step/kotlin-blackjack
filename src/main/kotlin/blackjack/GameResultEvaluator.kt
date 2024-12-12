package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.domain.calculateStatistics
import blackjack.infra.AmountStatistics
import blackjack.infra.AmountStatisticsBuilder
import blackjack.ui.DEALER_NAME
import blackjack.ui.DisplayCard
import blackjack.ui.RoundResult

class GameResultEvaluator(private val players: Players, private val dealer: Dealer) {
    fun evaluateRounds(): List<RoundResult> {
        val roundResults = mutableListOf<RoundResult>()

        val dealerCards: List<DisplayCard> = dealer.totalCards.cards.map { DisplayCard.from(it.rank.name, it.suit.name) }
        roundResults.add(
            RoundResult(
                DEALER_NAME,
                dealerCards,
                dealer.score().value,
            ),
        )

        players.forEach {
            val playerCards: List<DisplayCard> = it.totalCards.cards.map { card -> DisplayCard.from(card.rank.name, card.suit.name) }
            val roundResult = RoundResult(it.name, playerCards, it.score().value)
            roundResults.add(roundResult)
        }

        return roundResults
    }

    fun finalMatchEvaluate(): AmountStatistics {
        return dealer.calculateStatistics(players.values, AmountStatisticsBuilder())
    }
}
