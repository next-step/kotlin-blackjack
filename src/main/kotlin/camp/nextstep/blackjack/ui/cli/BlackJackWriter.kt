package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.game.GameResult
import camp.nextstep.blackjack.game.GameResults
import camp.nextstep.blackjack.game.Score
import camp.nextstep.blackjack.player.Gambler
import camp.nextstep.blackjack.player.Player

object BlackJackWriter {

    fun write(player: Player) {
        println(parse(player))
    }

    fun write(gamblers: List<Gambler>) {
        println("${gamblers.joinToString(",") { it.name }}에게 각각 ${BlackJackGame.INIT_CARD_NUMBER}장의 나누었습니다.")
    }

    fun write(gameResults: GameResults) {
        println()

        val (dealerScore, gamblersScore) = gameResults

        println(parse(dealerScore.dealer, dealerScore.score))
        for (gamblerScore in gamblersScore) {
            println(parse(gamblerScore.gambler, gamblerScore.score))
        }

        println("## 최종 승패")
        println("${dealerScore.dealer.name}: ${parse(dealerScore.results)}")
        for (gamblerScore in gamblersScore) {
            println("${gamblerScore.gambler.name}: ${parse(gamblerScore.result)}")
        }
    }

    private fun parse(player: Player, score: Score): String {
        return parse(player) + " - 결과 : " + parse(score)
    }

    private fun parse(player: Player): String {
        val faceUpCards = player.hand.faceUpCards.joinToString { "{${it.number}:${it.suit}}" }
        val faceDownCards = "{?:?}".repeat(player.hand.faceDownCardCount)
        return "${player.name}의 카드: $faceUpCards $faceDownCards"
    }

    private fun parse(score: Score): String {
        return score.value.toString()
    }

    private fun parse(results: List<GameResult>): String {
        return "${results.count { it == GameResult.WIN }}승 ${results.count { it == GameResult.LOSE }}패 ${results.count { it == GameResult.DRAW }}무"
    }

    private fun parse(result: GameResult): String {
        return when (result) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            else -> "무"
        }
    }
}
