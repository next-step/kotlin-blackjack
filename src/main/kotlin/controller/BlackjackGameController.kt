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

    private lateinit var game: BlackjackGame

    fun initGame(deckSize: Int = BlackjackGame.BLACKJACK_GAME_DECK_SIZE) {
        val playerNames = inputView.getPlayerNames()
        val playerBetAmounts = inputView.getPlayerBetAmounts(playerNames)
        game = BlackjackGame(deck = DeckGenerator.makeDeck(deckSize), playerBetAmounts = playerBetAmounts)
        game.initGame()
        resultView.printInitPlayers(players = game.players, dealer = game.dealer)
    }

    fun gameStart() {
        game.gameStart(isIssueCard = this::askPlayer, showMessage = this::showMessage)
    }

    private fun askPlayer(player: Player) = when (inputView.askDraw(player.name)) {
        Answer.YES -> true
        else -> false
    }

    private fun showMessage(player: Player) {
        if (player is Dealer) resultView.printDealerIssuedCardMessage() else resultView.printPlayerCards(player)
    }

    fun printGameResult() {
        val revenueResult = game.getPlayersRevenues()
        resultView.printIssuedCardResult(players = game.players, dealer = game.dealer)
        resultView.printRevenue(revenueResult)
    }
}
