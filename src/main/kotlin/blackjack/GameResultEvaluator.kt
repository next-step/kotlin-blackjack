package blackjack

import blackjack.domain.Dealer
import blackjack.domain.MatchType
import blackjack.domain.Players
import blackjack.ui.DEALER_NAME
import blackjack.ui.DealerResult
import blackjack.ui.DisplayCard
import blackjack.ui.FinalWinnerResults
import blackjack.ui.RoundResult
import blackjack.ui.UIMatchType

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

    fun finalMatchEvaluate(): FinalWinnerResults {
        val resultStatistics = dealer.dealerResult(players)

        val playerResults =
            dealer.playerResult(players).mapValues { (_, matchType) ->
                when (matchType) {
                    MatchType.WIN -> UIMatchType.WIN
                    MatchType.LOSE -> UIMatchType.LOSS
                    MatchType.DRAW -> UIMatchType.DRAW
                }
            }

        return FinalWinnerResults(
            DealerResult(
                wins = resultStatistics.winCount,
                losses = resultStatistics.loseCount,
                draws = resultStatistics.drawCount,
            ),
            playerResults,
        )
    }
}
