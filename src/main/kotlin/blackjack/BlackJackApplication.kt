package blackjack

import blackjack.controller.BlackJackController
import blackjack.repository.GameRepository
import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.OutputView

private val blackJackController = BlackJackController(BlackJackService(GameRepository()))

fun main() {
    val players = InputView.getPlayers()
    initGameSet(players)

    gameContinue()

    gameEnd()
}

private fun gameProgress(player: String) {
    val game = blackJackController.gameContinue(player)
    InputView.playerInfo(listOf(game))
}

fun initGameSet(players: List<String>) {
    blackJackController.initPlayers(players)
    InputView.gameStart(players)
}

fun gameContinue() {
    val playerInfos = blackJackController.startGame()
    InputView.playerInfo(playerInfos)
    playerInfos.forEach {
        while (InputView.isGameContinue(it.player)) {
            gameProgress(it.player)
        }
    }
}

fun gameEnd() {
    val results = blackJackController.getGameResult()
    OutputView.results(results)
}
