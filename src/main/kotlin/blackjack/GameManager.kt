package blackjack

import blackjack.business.CardDesk
import blackjack.business.PlayActions.dealInitialCards
import blackjack.business.PlayActions.printResult
import blackjack.business.PlayActions.processAdditionalCards
import blackjack.business.PlayerNameParser
import blackjack.business.Players
import blackjack.business.UserInputBasedDrawCondition
import blackjack.view.InputHandler

object GameManager {
    fun play() {
        val playerNames = InputHandler.askForPlayerNames()
        val players = Players.from(PlayerNameParser.parse(playerNames))
        val cardDesk = CardDesk()
        with(players) {
            dealInitialCards(cardDesk)
            processAdditionalCards(cardDesk, UserInputBasedDrawCondition())
            println()
            printResult()
        }
    }
}

fun main() {
    GameManager.play()
}
