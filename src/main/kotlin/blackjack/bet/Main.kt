package blackjack.bet

import blackjack.bet.view.BetInputView

fun main() {
    val playerNames = BetInputView.getPlayers()
    val betGame = BlackJackBetGame(playerNames)
    betGame.chargePhase { name -> BetInputView.chargeWallet(name) }
    betGame.startGame()
    betGame.processRound { name -> BetInputView.wantToHit(name) }
    betGame.endGame()
}
