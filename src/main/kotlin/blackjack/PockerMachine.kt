package blackjack

import blackjack.strategy.RandomCardPickStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class PockerMachine {
    fun execute() {
        val nameStr = InputView.readName()
        val people = People(nameStr)

        val dealer = Dealer(RandomCardPickStrategy())
        people.forEach { person -> initializeCard(dealer, person) }
        OutputView.printInitialState(people)

        people.forEach { person -> selectCard(person, dealer) }
        OutputView.printResult(people)
    }

    private fun initializeCard(dealer: Dealer, person: Person) {
        List(BASIC_CARD_COUNT) { person.apply { addCard(dealer.pickCard()) } }
    }

    private fun selectCard(person: Person, dealer: Dealer) {
        while (InputView.readRetry(person.name) == RETRY) {
            person.addCard(dealer.pickCard())
            OutputView.printCardState(person)
        }
        OutputView.printCardState(person)
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
        private const val RETRY = "y"
    }
}
