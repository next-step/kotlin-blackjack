package blackjack.controller

import blackjack.domain.bet.Money
import blackjack.domain.participant.state.Name
import blackjack.view.InputView

object InputFilter {
    fun inputPlayer(): Array<Name> {
        return runCatching {
            val parsedInput = InputParser.parseWithDelimiter(InputView.inputName())
            parsedInput.map { Name(it) }.toTypedArray()
        }.onFailure { e ->
            InputView.printError(e.message!!)
        }.getOrElse {
            inputPlayer()
        }
    }

    fun inputBettingMoney(name: String): Money {
        return runCatching {
            Money(InputParser.parseBettingMoney(InputView.inputBettingMoney(name)))
        }.onFailure { e ->
            InputView.printError(e.message!!)
        }.getOrElse {
            inputBettingMoney(name)
        }
    }

    fun inputHitOrStay(name: String): Boolean {
        return runCatching {
            InputParser.parseHitOrStay(InputView.inputHitOrStay(name))
        }.onFailure { e ->
            InputView.printError(e.message!!)
        }.getOrElse {
            inputHitOrStay(name)
        }
    }
}
