package blackjack.ui

import view.ConsoleInput
import view.ConsoleOutput

object BlackjackController {
    private val consoleInput = ConsoleInput()
    private val consoleOutput = ConsoleOutput()

    fun run() {
        consoleOutput.printUserNameInputMessage()
        consoleInput.read()
    }
}
