package blackjack

import blackjack.business.CardDesk
import blackjack.business.PlayerNameParser
import blackjack.business.Players
import blackjack.business.UserInputBasedDrawCondition
import blackjack.view.InputHandler
import blackjack.view.OutputHandler

object GameManager {
    fun play() {
        val playerNames = InputHandler.askForPlayerNames()
        val players = Players.from(PlayerNameParser.parse(playerNames))
        val cardDesk = CardDesk()
        with(players) {
            dealInitialCards(cardDesk, OutputHandler::printPlayer)
            processAdditionalCards(cardDesk, UserInputBasedDrawCondition(), OutputHandler::printPlayer)
            println()
            printResult(OutputHandler::printResult)
        }
    }
}

fun main() {
    GameManager.play()
}
