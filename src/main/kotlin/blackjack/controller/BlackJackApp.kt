package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Gamer
import blackjack.model.Player
import blackjack.model.Point
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.requestPlayerNames()
    val game = registerGame(playerNames)
    val gambleMoneyPerPlayer = InputView.requestGambleMoney(playerNames)
    ResultView.printPreview(game)

    drawCard(game)

    showSummary(game)
    showRevenue(game, gambleMoneyPerPlayer)
}

private fun showRevenue(game: BlackJackGame, gambleMoneyPerPlayer: List<Int>) {
    ResultView.printRevenue(game, gambleMoneyPerPlayer)
}

private fun showSummary(game: BlackJackGame) {
    (game.dealer as Dealer).requestCardIfPossibleExtraCard(Deck.pop())
    ResultView.printResult(game)
}

private fun drawCard(game: BlackJackGame) {
    game.players.map {
        while (isContinueDraw(it)) {
            it.requestCard(Deck.pop())
            ResultView.printCard(it)
        }
    }
}

private fun isContinueDraw(gamer: Gamer) =
    !Point.isReachMaxPoint(gamer.calculatePoint()) && InputView.requestOneOfCard(gamer) == "y"

private fun registerGame(playerNames: List<String>): BlackJackGame =
    BlackJackGame(dealer = Dealer(), players = playerNames.map(::Player)).apply {
        initCardForDealer()
        initCardForPlayers()
    }
