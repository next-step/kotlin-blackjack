package blackjack

import blackjack.domain.Deck
import blackjack.view.InputView
import blackjack.view.ResultView

private const val INIT_DRAW_CARD = 2

fun main() {
    val deck = Deck()
    val players = InputView.getPlayer()

    ResultView.printDistributeCard(INIT_DRAW_CARD, players)

    players.players.forEach {
        it.addCard(InputView.initDistributeCard(INIT_DRAW_CARD) { deck.draw() })
        InputView.printCurrentCard(it)
    }

    players.players.forEach {
        InputView.shareAnotherCard(it) { deck.draw() }
    }

    players.players.forEach {
        ResultView.printResult(it)
    }
}
