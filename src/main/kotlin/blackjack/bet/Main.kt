package blackjack.bet

import blackjack.bet.view.BetInputView
import blackjack.common.view.InputView

fun main() {
    val playerNames = InputView.getPlayers()
    val betGame = BlackJackBetGame(playerNames)
    betGame.chargePhase { name -> BetInputView.chargeWallet(name) }
    betGame.startGame()
    betGame.processRound()
    betGame.endGame()
}
