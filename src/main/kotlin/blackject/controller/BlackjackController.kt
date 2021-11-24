package blackject.controller

import blackject.model.Participant
import blackject.model.Person
import blackject.model.card.CardsDeck
import blackject.view.InputView
import blackject.view.OutputView

class BlackjackController(
    private val cardsDeck: CardsDeck,
) {

    fun start() {
        createGame().also {
            inputBetAmount(it)
            printCardListOfPerson(it)
            giveMoreCard(it)
            printResultScore(it)
            printResult(it)
        }
    }

    private fun createGame(): Participant {
        val persons = getParticipant()
        OutputView.printGivenCard(persons, cardsDeck.NUMBER_INIT_CARD)
        return persons
    }

    private fun printCardListOfPerson(persons: Participant) {
        persons.giveCards(CardsDeck.NUMBER_INIT_CARD) {
            OutputView.printCardListOfPerson(it)
        }
        println()
    }

    private fun inputBetAmount(persons: Participant) {
        persons.inputBetAmountByPerson {
            InputView.inputBetAmount(it)
        }
        println()
    }

    private fun giveMoreCard(persons: Participant) {
        persons.askMoreCard {
            askMoreCard(it)
        }

        persons.isTakeMoreCard { dealer ->
            dealer.giveCards(CardsDeck.NUMBER_ONE_TIME) {
                OutputView.printAddedDealerCard()
                return@giveCards
            }
        }
    }

    private fun printResultScore(persons: Participant) {
        println()
        persons.print { OutputView.gameResult(it) }
    }

    private fun printResult(persons: Participant) {
        OutputView.printProfit(persons.setGameResult())
    }

    private fun getParticipant(): Participant {
        InputView.inputParticipants().let {
            return Participant.addPerson(it)
        }
    }

    private fun askMoreCard(person: Person) {
        if (!person.canTakeMoreCard()) return
        if (!isAnswerYes(InputView.inputAnswerMoreCard(person.name))) return
        person.giveCards(CardsDeck.NUMBER_ONE_TIME) {
            OutputView.printCardListOfPerson(it)
        }
        askMoreCard(person)
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
