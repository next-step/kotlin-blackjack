import blackjack.domain.CardGenerator
import blackjack.entity.participantsFromNames
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val participants = participantName.participantsFromNames()
    OutputView.printParticipantsCard(participants)

    // PHASE 3
    val result = participants.map { participant ->
        while (participant.canGetCard) {
            OutputView.printGetMoreOneCard(participant.name)
            if (InputView.inputGetMoreCard().not()) break

            val newCard = CardGenerator.generateCard(1)
            participant.cards = participant.cards.copy(cards = participant.cards.cards + newCard)
            OutputView.printNewCards(participant)
        }
        participant.toString()
    }
    OutputView.printResult(result)
}
