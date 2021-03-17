package blackjack.ui

import blackjack.domain.PlayerFactory
import view.ConsoleInput
import view.ConsoleOutput

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        val players = PlayerFactory.create(consoleInput.read())
    }
}
