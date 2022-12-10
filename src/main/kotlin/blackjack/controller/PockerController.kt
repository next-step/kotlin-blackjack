package blackjack.controller

import blackjack.domain.Person
import blackjack.domain.PockerMachine
import blackjack.util.Parser
import blackjack.view.InputView
import blackjack.view.OutputView

class PockerController {
    fun execute() {
        val nameList = Parser.parse(InputView.readName())
        val people = nameList.map { Person(it) }
        val pockerMachine = PockerMachine()

        people.forEach { person ->
            pockerMachine.initialize(person)
        }
        OutputView.printInitialState(people)

        people.forEach { person ->
            pockerMachine.addCard(retryOrNot(), person)
            OutputView.printCardState(person)
        }
        OutputView.printResult(people)
    }

    private fun retryOrNot() = { person: Person ->
        val input = InputView.readRetry(person.name)
        validateRetryInput(input)
        input == RETRY
    }

    private fun validateRetryInput(input: String) {
        require(input in listOf(RETRY, RETRY_NOT)) { RETRY_INPUT_EXCEPTION }
    }

    companion object {
        private const val RETRY = "y"
        private const val RETRY_NOT = "n"
        private const val RETRY_INPUT_EXCEPTION = "y 혹은 n을 입력해야만해요."
    }
}
