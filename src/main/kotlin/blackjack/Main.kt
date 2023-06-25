package blackjack

import blackjack.controller.BlackJackController
import blackjack.service.BlackjackService

fun main() {
    BlackJackController(BlackjackService()).play()
}
