package blackject.controller

import blackject.model.Participant
import blackject.model.Person
import blackject.view.InputView
import blackject.view.OutputView

class BlackjectController {
    private val inputView = InputView()
    private val outView = OutputView()

    fun start() {
        val persons = getParticipant()
    }

    private fun getParticipant(): List<Person> {
        val names = inputView.inputParticipants()
        return Participant.addPerson(names).persons
    }
}
