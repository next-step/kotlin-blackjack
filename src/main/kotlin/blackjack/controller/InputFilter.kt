package blackjack.controller

import blackjack.domain.bet.Money
import blackjack.domain.participant.state.Name
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

    fun inputBettingMoney(name: String): Money {
        return try {
            Money(InputParser.parseBettingMoney(InputView.inputBettingMoney(name)))
        } catch (e: Exception) {
            InputView.printError(e.message!!)
            inputBettingMoney(name)
        }
    }

    fun inputHitOrStay(name: String): Boolean {
        return try {
            InputParser.parseHitOrStay(InputView.inputHitOrStay(name))
        } catch (e: Exception) {
            InputView.printError(e.message!!)
            inputHitOrStay(name)
        }
    }
}
