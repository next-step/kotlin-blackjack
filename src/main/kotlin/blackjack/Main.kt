package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Hands
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.ResultView.printPlayerInfo

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val cardDeck = CardDeck().shuffle()
    val players = playerNames.map { Player(it, Hands(cardDeck.firstDraw())) }

    ResultView.printInit(players)

    players.forEach {
        while (it.hands.isNotFinished() && InputView.willReceiveCard(it.name)) {
            it.hit(cardDeck.draw())
            printPlayerInfo(it)
        }
    }
}
