package blackjack

import blackjack.domain.Deck
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck()
    val players = InputView.getPlayer()

    ResultView.printDistributeCard(players)

    players.players.forEach {
        it.addCard(InputView.initDistributeCard() { deck.draw() })
        InputView.printCurrentCard(it)
    }

    players.players.forEach {
        val newCard = InputView.askGetCard(it.name) { deck.draw() }

        if (newCard.isNotEmpty()) {
            it.addCard(newCard)
        }
    }

    players.players.forEach {
        ResultView.printResult(it)
    }
}
