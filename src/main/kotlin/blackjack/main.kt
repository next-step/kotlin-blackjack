package blackjack

import blackjack.domain.BlackJack
import blackjack.views.InputView
import blackjack.views.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val players = inputView.receivePlayers()
    val blackJack = BlackJack(players)
    blackJack.drawInitialCards()
    outputView.printInitialDrawResult(blackJack.players)

    blackJack.players.forEach {
        while (true) {
            val receiveCard = inputView.receiveAnotherCard(it)
            if (receiveCard) {
                blackJack.drawAnotherCard(it)
                outputView.printPlayerStatus(it)
            } else {
                break
            }
        }
    }

    outputView.printResult(blackJack.players)
}
