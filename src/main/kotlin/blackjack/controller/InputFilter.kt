package blackjack.controller

import blackjack.domain.player.state.Name
import blackjack.view.InputView

object InputFilter {
    fun inputPlayer(): Array<Name> {
        return try {
            val parsedInput = InputParser.parseWithDelimiter(InputView.inputName())
            parsedInput.map { Name(it) }.toTypedArray()
        } catch (e: Exception) {
            InputView.printError(e.message!!)
            inputPlayer()
        }
    }
}
