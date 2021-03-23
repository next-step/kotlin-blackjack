package blackjack.ui

import blackjack.domain.DrawDecider
import blackjack.domain.GameTable
import blackjack.domain.card.CardDeck
import blackjack.domain.card.RANDOM_SHUFFLE
import blackjack.domain.player.Dealer
import blackjack.domain.player.PlayerFactory
import blackjack.domain.player.User
import view.ConsoleInput
import view.ConsoleOutput

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        val gameTable = GameTable(PlayerFactory.create(consoleInput.read()), CardDeck(RANDOM_SHUFFLE))

        gameTable.prepareGame { consoleOutput.printFirstDrawMessage(it) }

        gameTable.proceedGame(inputDraw(), printStatus()) { consoleOutput.printCardAndScore(it) }

        gameTable.endGame { consoleOutput.printGameRecord(it) }
    }

    private fun inputDraw(): (User) -> DrawDecider = inputDraw@{
        if (it is Dealer) {
            consoleOutput.printDealerDrawingMessage()
            return@inputDraw DrawDecider.DRAW
        }
        consoleOutput.printDecideDrawingMessage(it)
        DrawDecider.of(consoleInput.read())
    }

    private fun printStatus(): (User) -> Unit = { consoleOutput.printHandsStatus(it) }
}
