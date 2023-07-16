package blackjack

import blackjack.domain.Blackjack
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val blackjack = Blackjack(playerNames)
    val dealerAndPlayers: List<Player> = listOf(blackjack.dealer) + blackjack.players

    ResultView.printInit(dealerAndPlayers)

    blackjack.play()

    ResultView.printResult(dealerAndPlayers)
}
