package blackject.controller

import blackject.model.GameResult
import blackject.model.Participant
import blackject.model.Person
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
        persons
            .persons
            .forEach { person ->
                askMoreCard(person)
            }

        persons
            .dealer
            .let { dealer ->
                if (!dealer.canTakeMoreCard(rule.getMaxNumber(dealer.isPersonType()), rule.EXCEPT_NUMBER)) return
                dealer.giveCards(CardsDeck.NUMBER_ONE_TIME) {
                    if (dealer.isOverMaxInt(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER)) {
                        dealer.changeResultType(ResultType.BUST)
                        return@giveCards
                    }
                    OutputView.printAddedDealerCard()
                    return@giveCards
                }
            }
    }

    private fun printResultScore(persons: Participant) {
        println()
        persons
            .getAllPerson()
            .forEach { OutputView.gameResult(it, rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) }
    }

    private fun printResult(persons: Participant) {
        val winScore = GameResult.getWinNumber(
            rule.MAX_TOTAL_NUMBER,
            persons
                .getAllPerson()
                .map { it.getScore(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) }
        )

        persons
            .getAllPerson()
            .filterNot { it.hasResult() }
            .forEach {
                when {
                    it.getScore(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) == winScore -> it.changeResultType(ResultType.WIN)
                    it.getScore(rule.MAX_TOTAL_NUMBER, rule.EXCEPT_NUMBER) > rule.MAX_TOTAL_NUMBER -> it.changeResultType(ResultType.BUST)
                    else -> it.changeResultType(ResultType.LOSE)
                }
            }
        OutputView.gameWinDefeat(persons)
    }

    private fun getParticipant(): Participant {
        InputView.inputParticipants().let {
            return Participant.addPerson(it)
        }
    }

    private fun askMoreCard(person: Person) {
        if (!person.canTakeMoreCard(rule.getMaxNumber(person.isPersonType()), rule.EXCEPT_NUMBER)) return
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
