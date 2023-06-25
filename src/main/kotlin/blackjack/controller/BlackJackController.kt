package blackjack.controller

import blackjack.service.BlackjackService

class BlackJackController(
    private val blackjackService: BlackjackService
) {

    fun play() {

        val blackjackGame = blackjackService.initBlackjackGame()
    }
}
