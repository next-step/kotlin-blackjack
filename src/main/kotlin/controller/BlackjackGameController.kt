package controller

import domain.card.util.DeckGenerator
import domain.game.BlackjackGame
import domain.player.BetAmount
import domain.player.Dealer
import domain.player.Player
import domain.player.PlayerBetAmount
import domain.player.PlayerBetAmounts
import view.Answer
import view.InputView
import view.ResultView

class BlackjackGameController(
    private val inputView: InputView,
    private val resultView: ResultView,
) {

    private val game: BlackjackGame

    init {
        val playerNames = inputView.getPlayerNames()
        val betAmounts = inputView.getPlayerBetAmounts(playerNames)
        val playerBetAmounts = PlayerBetAmounts(
            betAmounts.map { (name, betAmount) ->
                PlayerBetAmount(
                    name = name,
                    betAmount = BetAmount(betAmount),
                )
            },
        )
        game = BlackjackGame(deck = DeckGenerator.makeDeck(), playerBetAmounts = playerBetAmounts)
    }

    fun gameStart() {
        initGame()
        game.gameStart(isIssueCard = this::askPlayer, showMessage = this::showMessage)
        printGameResult()
    }

    private fun initGame() {
        game.initGame()
        resultView.printInitPlayers(players = game.players, dealer = game.dealer)
    }

    private fun printGameResult() {
        val revenueResult = game.getPlayersRevenues()
        resultView.printIssuedCardResult(players = game.players, dealer = game.dealer)
        resultView.printRevenue(revenueResult)
    }

    private fun askPlayer(player: Player) = when (inputView.askDraw(player.name)) {
        Answer.YES -> true
        else -> false
    }

    private fun showMessage(player: Player) {
        if (player is Dealer) resultView.printDealerIssuedCardMessage() else resultView.printPlayerCards(player)
    }
}
