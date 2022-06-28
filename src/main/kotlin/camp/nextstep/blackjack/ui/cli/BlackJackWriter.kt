package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.game.GameResults
import camp.nextstep.blackjack.player.Gambler
import camp.nextstep.blackjack.ui.DealerResults
import camp.nextstep.blackjack.ui.PlayerHand
import camp.nextstep.blackjack.ui.PlayerResult
import camp.nextstep.blackjack.ui.PlayerScore

object BlackJackWriter {

    fun write(playerHand: PlayerHand) {
        println(playerHand)
    }

    fun write(gamblers: List<Gambler>) {
        println("${gamblers.joinToString(",") { it.name }}에게 각각 ${BlackJackGame.INIT_CARD_NUMBER}장의 나누었습니다.")
    }

    fun write(gameResults: GameResults) {
        println()

        val (dealerScore, gamblersScore) = gameResults

        println(PlayerScore(dealerScore.dealer, dealerScore.score))
        for (gamblerScore in gamblersScore) {
            println(PlayerScore(gamblerScore.gambler, gamblerScore.score))
        }

        println("## 최종 승패")
        println("${dealerScore.dealer.name}: ${DealerResults(dealerScore.results)}")
        for (gamblerScore in gamblersScore) {
            println("${gamblerScore.gambler.name}: ${PlayerResult(gamblerScore.result)}")
        }
    }
}
