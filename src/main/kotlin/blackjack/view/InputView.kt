package blackjack.view

import blackjack.model.candidate.CandidateName
import blackjack.model.candidate.Player

interface InputView {
    fun printPlayerNamesInputMessage()

    fun inputPlayerNames(): List<Player>

    fun printNeedMoreCardAskMessage(name: CandidateName)

    fun inputWhetherNeedMoreCard(): String
}
