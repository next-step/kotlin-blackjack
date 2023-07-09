package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.view.InputView

fun main() {
    val blackJackGame = BlackJackGame(
        Players.of(InputView.player()),
        Deck.create()
    )
}
