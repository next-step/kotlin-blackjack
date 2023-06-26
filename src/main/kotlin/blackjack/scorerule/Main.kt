package blackjack.scorerule

import blackjack.common.view.InputView

fun main() {
    val playerNames = InputView.getPlayers()
    val blackJackScoreGame = BlackJackScoreGame(playerNames)
    blackJackScoreGame.startGame()
    blackJackScoreGame.processRound()
    blackJackScoreGame.endGame()
}
