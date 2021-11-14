package blackject.controller

import blackject.model.Participant
import blackject.model.Person
import blackject.model.card.CardsDeck
import blackject.view.InputView
import blackject.view.OutputView

class BlackjectController {
    private val inputView = InputView()
    private val outView = OutputView()

    fun start() {
        val persons = getParticipant()
        outView.printGivenCard(persons, CardsDeck.NUMBER_INIT_CARD)
        persons
            .forEach { giveCard(it, CardsDeck.NUMBER_INIT_CARD) }
        persons
            .forEach { askMoreCard(it) }
        persons
            .forEach { gameResult(it) }
    }

    private fun getParticipant(): List<Person> {
        val names = inputView.inputParticipants()
        return Participant.addPerson(names).persons
    }

    private fun giveCard(person: Person, cardCount: Int, isPrintCardList: Boolean = true) {
        val cards = CardsDeck.takeCard(cardCount)
        person.cardList.addAll(0, cards)

        if (!isPrintCardList) return
        println("${person.name}카드: ${person.cardList.joinToString { it.cardName }}")
    }

    private fun askMoreCard(person: Person) {
        while (true) {
            if (!person.isTakeMoreCard()) break
            val ask = inputView.inputAnswerMoreCard(person.name)
            if (!isAnswerYes(ask)) break
            giveCard(person, CardsDeck.NUMBER_ONE_TIME, true)
        }
    }

    private fun gameResult(person: Person) {
        println("${person.name}카드: ${person.cardList.joinToString { it.cardName }} - 결과: ${person.getResultNumber()}")
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
