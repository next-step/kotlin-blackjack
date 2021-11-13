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
        persons.forEach { giveCard(it, CardsDeck.NUMBER_INIT_CARD) }
        persons.forEach { askMoreCard(it) }
    }

    private fun getParticipant(): List<Person> {
        val names = inputView.inputParticipants()
        return Participant.addPerson(names).persons
    }

    private fun giveCard(person: Person, cardCount: Int, isPrintCardList: Boolean = true) {
        val cards = CardsDeck.takeCard(cardCount)
        person.cardList?.addAll(cards)

        if (!isPrintCardList) return
        println("${person.name}카드: ${person.cardList?.joinToString { it.cardName }}")
    }

    private fun askMoreCard(person: Person) {
        while (true) {
            val ask = inputView.inputAnswerMoreCard(person.name)
            if (!isAnswerYes(ask)) break
            giveCard(person, CardsDeck.NUMBER_ONE_TIME, true)
        }
    }

    companion object {
        private const val ANSWER_YES = "y"
        private const val ANSWER_NO = "n"

        fun isAnswerYes(answer: String?): Boolean {
            require(!answer.isNullOrEmpty())
            return when (answer) {
                ANSWER_YES -> true
                ANSWER_NO -> false
                else -> false
            }
        }
    }
}
