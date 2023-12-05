package blackjack

import blackjack.controller.BlackjackController
import blackjack.domain.Dealer
import blackjack.domain.Deck

fun main() {
    val controller = BlackjackController(Dealer(), Deck())
    controller.handle()
}
