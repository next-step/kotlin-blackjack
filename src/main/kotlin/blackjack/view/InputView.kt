package blackjack.view

import blackjack.model.candidate.Candidate
import blackjack.model.candidate.CandidateName

interface InputView {
    fun printPlayerNamesInputMessage()

    fun inputPlayerNames(): List<Candidate>

    fun printNeedMoreCardAskMessage(name: CandidateName)

    fun inputWhetherNeedMoreCard(): String
}
