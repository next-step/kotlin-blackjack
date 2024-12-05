package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.MatchType
import blackjack.domain.Players
import blackjack.ui.DealerResult
import blackjack.ui.FinalWinnerResults
import blackjack.ui.RoundResult
import blackjack.ui.UIMatchType

const val DEALER_NAME = "딜러"

class GameResultEvaluator(private val players: Players, private val dealer: Dealer) {
    private fun groupCardsByRank(cards: List<Card>): Map<String, List<String>> =
        cards.groupBy { it.rank.name }
            .map { (rank, cards) ->
                rank to cards.map { it.suit.name }
            }.toMap()

    fun evaluateRounds(): List<RoundResult> {
        val roundResults = mutableListOf<RoundResult>()
        players.forEach {
            val roundResult = RoundResult.from(it.name, groupCardsByRank(it.totalCards.cards), it.score().value)
            roundResults.add(roundResult)
        }

        roundResults.add(
            RoundResult.from(
                DEALER_NAME,
                groupCardsByRank(dealer.totalCards.cards),
                dealer.score().value,
            ),
        )

        return roundResults
    }

    fun finalMatchEvaluate(): FinalWinnerResults {
        val resultStatistics = dealer.dealerResult(players)

        val playerResults =
            dealer.judge(players).mapValues { (_, matchType) ->
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
