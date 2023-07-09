package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Hands
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val cardDeck = CardDeck().shuffle()
    val players = playerNames.map { Player(it, Hands(cardDeck.firstDraw())) }

    ResultView.init(players)
}
