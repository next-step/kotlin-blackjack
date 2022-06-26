package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.GameResult

object GameResultWriter {

    fun write(gameResult: GameResult) {
        println()
        for (gamblerScore in gameResult.gamblersScore) {
            val gambler = gamblerScore.gambler.name
            val gamblerCards = gamblerScore.gambler.hand.cards.map { it.card }.joinToString(",") { "{${it.number.value}:${it.suit}}" }
            val score = gamblerScore.score.value

            println("${gambler}카드: $gamblerCards - 결과: $score")
        }
    }
}
