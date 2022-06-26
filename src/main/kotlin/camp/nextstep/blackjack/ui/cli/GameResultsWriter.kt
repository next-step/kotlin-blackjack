package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.GameResult
import camp.nextstep.blackjack.game.GameResults

object GameResultsWriter {

    fun write(gameResults: GameResults) {
        println()

        val (dealerScore, gamblersScore) = gameResults

        DealerCardsWriter.write(dealerScore.dealer)
        println(" - 결과: ${dealerScore.score.value}")

        for (gamblerScore in gamblersScore) {
            GamblerCardsWriter.write(gamblerScore.gambler)
            println(" - 결과: ${gamblerScore.score.value}")
        }

        println("## 최종 승패")
        println("딜러: ${dealerScore.results.count { it == GameResult.WIN }}승 ${dealerScore.results.count { it == GameResult.LOSE }}패 ${dealerScore.results.count { it == GameResult.DRAW }}무")
        for (gamblerScore in gamblersScore) {
            println("${gamblerScore.gambler.name}: ${resultString(gamblerScore.result)}")
        }
    }

    private fun resultString(result: GameResult): String {
        return when (result) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            else -> "무"
        }
    }
}
