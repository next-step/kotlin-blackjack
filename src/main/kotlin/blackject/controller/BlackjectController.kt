package blackject.controller

import blackject.model.Participant
import blackject.model.Person
import blackject.model.card.CardsDeck
import blackject.view.InputView
import blackject.view.OutputView

class BlackjectController {
    fun start() {
        val persons = getParticipant()
        OutputView.printGivenCard(persons, CardsDeck.NUMBER_INIT_CARD)
        persons
            .forEach {
                giveCard(it, CardsDeck.NUMBER_INIT_CARD)
                OutputView.printCardListOfPerson(it)
            }
        persons
            .forEach { askMoreCard(InputView.inputAnswerMoreCard(it.name), it) }
        persons
            .forEach { OutputView.gameResult(it) }
    }

    private fun getParticipant(): List<Person> {
        val names = InputView.inputParticipants()
        return Participant.addPerson(names).persons
    }

    private fun giveCard(person: Person, cardCount: Int) {
        person.addCard(CardsDeck.takeCard(cardCount))
    }

    private fun askMoreCard(answer: String?, person: Person) {
        when (isAnswerYes(answer)) {
            true -> {
                if (!person.isTakeMoreCard()) return
                giveCard(person, CardsDeck.NUMBER_ONE_TIME)
                OutputView.printCardListOfPerson(person)
                askMoreCard(InputView.inputAnswerMoreCard(person.name), person)
            }
            else -> return
        }
    }

    companion object {
        private const val ANSWER_YES = "y"
        private const val ANSWER_NO = "n"

        fun isAnswerYes(answer: String?): Boolean {
            require(!answer.isNullOrEmpty())
            require(answer == ANSWER_NO || answer == ANSWER_YES)
            return when (answer) {
                ANSWER_YES -> true
                ANSWER_NO -> false
                else -> false
            }
        }
    }
}
