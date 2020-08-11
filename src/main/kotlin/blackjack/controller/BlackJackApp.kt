package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Card
import blackjack.model.Gamer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    registerGame().let {
        drawCard(it)
        showResult(it)
    }
}

private fun showResult(game: BlackJackGame) {
    game.players.map { ResultView.printResult(it, it.totalPoints) }
}

private fun drawCard(game: BlackJackGame) {
    game.players.map {
        while (isContinueDraw(it)) {
            it.requestCard(Card.pop())
            ResultView.printPlayerHaveCard(it)
        }
    }
}

private fun isContinueDraw(player: Gamer) =
    !player.isReachMaxPoint() && InputView.requestOneOfCard(player) == "y"

private fun registerGame(): BlackJackGame {
    val playerName = InputView.requestPlayerNames()
    val blackJackGame = BlackJackGame(playerName.map(::Player))
    ResultView.printPreGame(blackJackGame)
    return blackJackGame
}
