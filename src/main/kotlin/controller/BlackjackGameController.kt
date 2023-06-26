package controller

import domain.card.util.DeckGenerator
import domain.game.BlackjackGame
import domain.player.Dealer
import domain.player.Player
import view.Answer
import view.InputView
import view.ResultView

class BlackjackGameController(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun initGame(deckSize: Int): BlackjackGame {
        val playerNames = inputView.getPlayerNames()
        val playerBetAmounts = inputView.getPlayerBetAmounts(playerNames)
        val game = BlackjackGame(deck = DeckGenerator.makeDeck(deckSize), playerBetAmounts = playerBetAmounts)
        resultView.printInitPlayers(players = game.players, dealer = game.dealer)
        return game
    }

    fun gameStart(game: BlackjackGame) {
        game.gameStart(isIssueCard = this::askPlayer, showMessage = this::showMessage)
    }

    private fun askPlayer(player: Player) = when (inputView.askDraw(player.name)) {
        Answer.YES -> true
        else -> false
    }

    private fun showMessage(player: Player) {
        if (player is Dealer) resultView.printDealerIssuedCardMessage() else resultView.printPlayerCards(player)
    }

    fun printGameResult(game: BlackjackGame) {
        val revenueResult = game.getPlayersRevenues()
        resultView.printIssuedCardResult(players = game.players, dealer = game.dealer)
        resultView.printRevenue(revenueResult)
    }
}
