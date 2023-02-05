package blackjack.controller

import blackjack.domain.bet.Money
import blackjack.domain.participant.state.Name
import blackjack.dto.Input
import blackjack.view.InputView

object InputFilter : Input {
    override fun inputPlayer(): Array<Name> {
        return runCatching {
            val parsedInput = InputParser.parseWithDelimiter(InputView.inputName())
            parsedInput.map { Name(it) }.toTypedArray()
        }.onFailure { e ->
            e.message?.let(InputView::printError)
        }.getOrElse {
            inputPlayer()
        }
    }

    override fun inputBettingMoney(name: String): Money {
        return runCatching {
            Money(InputParser.parseBettingMoney(InputView.inputBettingMoney(name)))
        }.onFailure { e ->
            e.message?.let(InputView::printError)
        }.getOrElse {
            inputBettingMoney(name)
        }
    }

    override fun inputHitOrStay(name: String): Boolean {
        return runCatching {
            InputParser.parseHitOrStay(InputView.inputHitOrStay(name))
        }.onFailure { e ->
            e.message?.let(InputView::printError)
        }.getOrElse {
            inputHitOrStay(name)
        }
    }
}
