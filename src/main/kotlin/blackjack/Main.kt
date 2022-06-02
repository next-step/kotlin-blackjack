package blackjack

import blackjack.domain.card.CardDeck
import blackjack.view.InputView

fun main() {

    val inputView = InputView()
    val playerNames = inputView.enterPlayerName()
    val cardDeck = CardDeck()

}
