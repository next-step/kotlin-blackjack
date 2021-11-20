package blackject.controller

import blackject.model.GameResult
import blackject.model.Participant
import blackject.model.Person
import blackject.model.PersonType
import blackject.model.ResultType
import blackject.model.Rule
import blackject.model.card.CardsDeck
import blackject.view.InputView
import blackject.view.OutputView

class BlackjectController(
    private val rule: Rule,
    private val cardsDeck: CardsDeck,
) {

    fun start() {
        createGame().let {
            printCardListOfPerson(it)
            giveMoreCard(it)
            printResultScore(it)
            printResult(it)
        }
    }

    private fun createGame(): List<Person> {
        val persons = getParticipant()
        OutputView.printGivenCard(persons, cardsDeck.NUMBER_INIT_CARD)
        return persons
    }

    private fun printCardListOfPerson(persons: List<Person>) {
        persons
            .forEach {
                giveCard(it, CardsDeck.NUMBER_INIT_CARD)
                OutputView.printCardListOfPerson(it)
            }
        println()
    }

    private fun giveMoreCard(persons: List<Person>) {
        persons
            .forEach {
                if (!it.isTakeMoreCard(rule.getMaxNumber(it.type), rule.EXCEPT_NUMBER)) return@forEach
                if (it.type == PersonType.DEALER) {
                    giveCard(it, CardsDeck.NUMBER_ONE_TIME)
                    if (it.cards.getResultNumber(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) > rule.MAX_TOTAL_NUMBER) {
                        it.setGameResult(ResultType.BUST)
                        return
                    }
                    OutputView.printAddedDealerCard(rule.MAX_NUMBER_DEALER)
                    return@forEach
                }
                askMoreCard(InputView.inputAnswerMoreCard(it.name), it)
            }
        println()
    }

    private fun printResultScore(persons: List<Person>) {
        println()
        persons
            .forEach { OutputView.gameResult(it, rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) }
    }

    private fun printResult(persons: List<Person>) {
        val winScore = GameResult.getWinNumber(
            rule.MAX_TOTAL_NUMBER,
            persons.map { it.cards.getResultNumber(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) }
        )

        persons
            .filterNot { it.result != null }
            .forEach {
                when {
                    it.getScore(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) == winScore -> it.setGameResult(ResultType.WIN)
                    it.getScore(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) > rule.MAX_TOTAL_NUMBER -> it.setGameResult(ResultType.BUST)
                    else -> it.setGameResult(ResultType.DEFEAT)
                }
            }
        OutputView.gameWinDefeat(persons)
    }

    private fun getParticipant(): List<Person> {
        InputView.inputParticipants().let {
            return Participant.addPerson(it).persons
        }
    }

    private fun giveCard(person: Person, cardCount: Int) {
        person.giveCard(CardsDeck.takeCard(cardCount))
    }

    private fun askMoreCard(answer: String?, person: Person) {
        if (!isAnswerYes(answer)) return
        giveCard(person, CardsDeck.NUMBER_ONE_TIME)
        OutputView.printCardListOfPerson(person)
        if (!person.isTakeMoreCard(rule.getMaxNumber(person.type), rule.EXCEPT_NUMBER)) return
        askMoreCard(InputView.inputAnswerMoreCard(person.name), person)
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
