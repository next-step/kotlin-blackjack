package blackjack

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.UserInputBasedDrawCondition
import blackjack.business.participants.Players
import blackjack.business.util.PlayerNameParser
import blackjack.view.DetailedPlayerOutputHandler
import blackjack.view.InputHandler
import blackjack.view.SimplePlayerOutputHandler

object GameManager {
    fun play() {
        val playerNames = InputHandler.askForPlayerNames()
        val players = Players.from(PlayerNameParser.parse(playerNames))
        val cardDesk = CardDesk()
        val simplePlayerOutputHandler = SimplePlayerOutputHandler()
        val detailedPlayerOutputHandler = DetailedPlayerOutputHandler()
        with(players) {
            dealInitialCards(cardDesk, simplePlayerOutputHandler::print)
            processAdditionalCards(cardDesk, UserInputBasedDrawCondition(), simplePlayerOutputHandler::print)
            println()
            forEachPlayer(detailedPlayerOutputHandler::print)
        }
    }
}

fun main() {
    GameManager.play()
}
