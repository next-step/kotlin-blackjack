package controller

import domain.game.BlackjackGame
import domain.player.Player
import view.Answer
import view.InputView
import view.ResultView

class BlackjackGameController(
    private val game: BlackjackGame,
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun initGame() {
        val playerNames = inputView.getPlayerNames()
        game.initGame(playerNames)
        resultView.printInitPlayers(players = game.players, dealer = game.dealer)
    }

    fun gameStart() {
        game.gameStart(isIssueCard = this::askPlayer, showPlayerCards = this::showPlayerCards)
    }

    private fun askPlayer(playerName: String) = when (inputView.askDraw(playerName)) {
        Answer.YES -> true
        else -> false
    }

    private fun showPlayerCards(player: Player) { resultView.printPlayerCards(player) }

    fun playDealer() {
        val issueCardForDealerResult = game.issuedCardForDealer()
        if (issueCardForDealerResult) {
            resultView.printDealerIssuedCardMessage()
        }
    }

    fun printGameResult() {
        val gameWinLoseDrawResult = game.getGameWinLoseDrawResult()
        resultView.printIssuedCardResult(players = game.players, dealer = game.dealer)
        resultView.printWinLoseDrawResult(gameWinLoseDrawResult)
    }
}
