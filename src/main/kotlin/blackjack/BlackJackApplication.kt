package blackjack

import blackjack.controller.BlackJackController
import blackjack.entity.Player
import blackjack.repository.GameRepository
import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.OutputView

private val blackJackController = BlackJackController(BlackJackService(GameRepository()))

fun main() {
    val playersList = InputView.getPlayers()
    val dealerName = InputView.getDealerName()
    initGameSet(dealerName, playersList)

    gameStart()

    gameContinue()

    gameCardResult()

    gameEnd()
}

private fun gameCardResult() {
    val game = blackJackController.getGameInfo()
    OutputView.gameCardResult(game)
}

private fun gameProgress(player: String): Boolean {
    val player = blackJackController.gameContinue(player)
    InputView.playerInfo(player)
    return player.getPlayerBlackJack().isBust()
}

private fun gameStart() {
    val game = blackJackController.startGame()
    OutputView.gameStart(game)
}

fun initGameSet(dealerName: String, playersList: List<String>) {
    blackJackController.initPlayers(dealerName, playersList)
}

fun gameContinue() {
    val game = blackJackController.getGameInfo()
    val playersList = game.players
    val dealer = game.dealer
    val isLessThanSixTeen = dealer.getDealerBlackJack().isLessThanSixTeen()
    playersList.forEach { player ->
        playerGameProgress(player)
    }
    if (isLessThanSixTeen) {
        dealerGameProgress(dealer.name)
    }
}

private fun playerGameProgress(player: Player) {
    while (InputView.isGameContinue(player.name)) {
        val isBust = gameProgress(player.name)
        if (isBust) break
    }
}

private fun dealerGameProgress(dealerName: String) {
    InputView.dealerAddCardComment(dealerName)
    blackJackController.gameContinueDealer()
}


fun gameEnd() {
    val results = blackJackController.getGameResult()
    OutputView.results(results)
}
