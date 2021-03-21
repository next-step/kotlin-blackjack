package blackjack.ui

import blackjack.domain.DrawDecider
import blackjack.domain.GameTable
import blackjack.domain.card.CardDeck
import blackjack.domain.card.RandomShuffleStrategy
import blackjack.domain.player.PlayerFactory
import view.ConsoleInput
import view.ConsoleOutput

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        val gameTable = GameTable(PlayerFactory.create(consoleInput.read()), CardDeck(RandomShuffleStrategy()))

        gameTable.prepareGame {
            consoleOutput.printFirstDrawMessage(it)
        }

        gameTable.proceedGame({
            consoleOutput.printDecideDrawingMessage(it)
            DrawDecider.of(consoleInput.read())
        }) { consoleOutput.printHandsStatus(it) }

        gameTable.endGame {
            consoleOutput.printGameResult(it)
        }
    }
}
