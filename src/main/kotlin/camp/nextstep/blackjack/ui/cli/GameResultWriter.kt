package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.GameResult

object GameResultWriter {

    fun write(gameResult: GameResult) {
        println()
        for (playerScore in gameResult.playerScores) {
            val player = playerScore.player.name
            val playerCards = playerScore.player.cards.joinToString(",") { "{${it.number.value}:${it.suit}}" }
            val score = playerScore.score.value

            println("${player}카드: $playerCards - 결과: $score")
        }
    }
}
