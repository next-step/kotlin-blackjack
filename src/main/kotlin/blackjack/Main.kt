package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val players = inputView.inputPlayerNames()
        .map { Player(it) }
        .let { Players(it) }
    val blackjackGame = BlackjackGame(players)

    blackjackGame.dealInitialHand()
    resultView.outputInitialHand(players)

    players.forEach { player ->
        while (player.canHit) {
            val hit = inputView.inputHitDecision(player)
            if (hit) {
                blackjackGame.dealCardTo(player)
                resultView.outputCurrentHand(player)
            } else {
                player.stay()
            }
        }
    }

    resultView.outputGameResult(players)
}
