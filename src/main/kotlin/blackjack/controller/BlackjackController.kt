package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.view.enterUserNames
import blackjack.view.printUserNames

class BlackjackController {
    fun start() {
        val userNames = enterUserNames().trim()
        val blackjackGame = BlackjackGame(userNames)
        printUserNames(userNames)
    }
}
