package controller

import domain.game.BlackjackGame
import domain.player.Player
import view.Answer
import view.InputView
import view.ResultView

class BlackjackGameController(
    private val game: BlackjackGame,
    private val inputView: InputView,
    private val resultView: ResultView
) {
    fun initGame() {
        val playerNames = inputView.getPlayerNames()
        game.initGame(playerNames)
        resultView.printInitPlayers(players = game.players)
    }

    fun gameStart() {
        game.players.forEach { player ->
            while (!game.isTerminatedPlayer(player)) {
                gameProgress(inputView, player)
                resultView.printPlayerCards(player)
            }
        }
    }

    private fun gameProgress(inputView: InputView, player: Player) {
        when (inputView.askDraw(player.name)) {
            Answer.YES -> game.issueCard(player)
            else -> game.stopIssueCard(player)
        }
    }

    fun printGameResult() {
        resultView.printGameResult(players = game.players)
    }
}
