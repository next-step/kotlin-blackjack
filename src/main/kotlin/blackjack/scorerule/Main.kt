package blackjack.scorerule

import blackjack.common.view.CommonInputView

fun main() {
    val playerNames = CommonInputView.getPlayers()
    val blackJackScoreGame = BlackJackScoreGame(playerNames)
    blackJackScoreGame.startGame()
    blackJackScoreGame.processRound()
    blackJackScoreGame.endGame()
}
