package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val players = inputView.getPlayerNames().map { Player(it) }
    val blackJackGame = BlackJackGame()

    blackJackGame.startGame(players)
    outputView.renderStartGame(players)

    players.forEach {
        while (it.canHit() && inputView.isHit(it)) {
            blackJackGame.hit(it)
            outputView.renderPlayerCards(it)
        }
    }
    outputView.renderResult(players)
}
