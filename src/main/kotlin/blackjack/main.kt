package blackjack

import blackjack.domain.BlackJack
import blackjack.views.InputView
import blackjack.views.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val players = inputView.receivePlayers()
    val blackJack = BlackJack(players)

    blackJack.apply {
        drawInitialCards()
        outputView.printInitialDrawResult(players)

        players.forEach {
            while (it.canTakeCards) {
                val drawCard = inputView.receiveCardDrawDecision(it)
                if (drawCard) {
                    drawAnotherCard(it)
                    outputView.printPlayerStatus(it)
                } else {
                    break
                }
            }
        }
    }

    outputView.printResult(blackJack.players)
}
