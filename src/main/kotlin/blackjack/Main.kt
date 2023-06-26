package blackjack

import blackjack.view.InputView

fun main() {
    val players = InputView.getPlayers()
    val blackJackGame = BlackJackGame(players)
    blackJackGame.startGame()
    blackJackGame.processRound()
    blackJackGame.endGame()
}
