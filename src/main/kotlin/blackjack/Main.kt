package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Hands
import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val cardDeck = CardDeck().shuffle()
    val players = playerNames.map { Player(it, Hands(cardDeck.firstDraw())) }
}
