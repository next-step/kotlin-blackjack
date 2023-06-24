package controller

import domain.game.BlackjackGame
import domain.player.Player
import domain.player.Players
import view.Answer
import view.InputView
import view.ResultView

class BlackjackGameController(
    private val game: BlackjackGame,
    private val inputView: InputView,
    private val resultView: ResultView
) {
    fun initGame(): Players {
        val playerNames = inputView.getPlayerNames()
        val players = game.initGame(playerNames)
        resultView.printInitPlayers(players = players)
        return players
    }

    fun gameStart(
        player: Player,
        inputView: InputView,
        resultView: ResultView,
    ) {
        while (!game.isTerminatedPlayer(player)) {
            gameProgress(inputView, player)

            resultView.printPlayerCards(player)
        }
    }

    private fun gameProgress(inputView: InputView, player: Player) {
        when (inputView.askDraw(player.name)) {
            Answer.YES -> game.issueCard(player)
            else -> game.stopIssueCard(player)
        }
    }

    fun printGameResult(players: Players) {
        resultView.printGameResult(players = players)
    }
}
