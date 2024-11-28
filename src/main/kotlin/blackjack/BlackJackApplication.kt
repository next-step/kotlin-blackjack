package blackjack

import blackjack.controller.BlackJackController
import blackjack.repository.GameRepository
import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.OutputView

private val blackJackController = BlackJackController(BlackJackService(GameRepository()))

fun main() {
    val players = InputView.getPlayers()
    blackJackController.initPlayers(players)
    InputView.gameStart(players)
    val playerInfos = blackJackController.startGame()
    InputView.playerInfo(playerInfos)

    players.forEach { player ->
        while (true) {
            val isProgress = InputView.gameContinue(player).lowercase()
            if ("y".equals(isProgress)) {
                gameProgress(player)
            } else {
                break
            }
        }
    }
    val results = blackJackController.getGameResult()
    OutputView.results(results)
}

private fun gameProgress(player: String) {
    val game = blackJackController.gameContinue(player)
    InputView.playerInfo(listOf(game))
}
