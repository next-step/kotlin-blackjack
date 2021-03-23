package blackjack.ui

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardDeck
import blackjack.domain.card.RANDOM_SHUFFLE
import blackjack.domain.player.UserFactory
import view.ConsoleInput
import view.ConsoleOutput

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        val users = UserFactory.create(consoleInput.read())
        val cardDeck = CardDeck(RANDOM_SHUFFLE)

        users.drawAtFirst(cardDeck)
        consoleOutput.printFirstDrawMessage(users.toUserInfo())

        users.doPlayers {
            consoleOutput.printDecideDrawingMessage(it)
            it.draw(cardDeck.pop(), DrawDecider.of(consoleInput.read()))
            consoleOutput.printHandsStatus(it)
        }

        users.doDealer {
            it.drawAdditional(cardDeck)
            consoleOutput.printDealerDrawingMessage()
        }

        consoleOutput.printCardAndScore(users.toUserInfo())
        consoleOutput.printGameRecord(users.getResult())
    }
}
