package blackjack.view

import blackjack.model.candidate.CandidateName

interface InputView {
    fun printPlayerNamesInputMessage()

    fun inputPlayerNames(): List<String>

    fun printPlayerBettingAmountInputMessage(name: String)

    fun inputPlayerBettingAmount(): Int

    fun printNeedMoreCardAskMessage(name: CandidateName)

    fun inputWhetherNeedMoreCard(): String
}
