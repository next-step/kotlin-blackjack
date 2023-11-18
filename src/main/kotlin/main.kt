import blackjack.BlackJack
import blackjack.entity.ParticipantCards
import blackjack.entity.ParticipantState
import blackjack.entity.participantsFromNames
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.printer.ParticipantPrinter

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val cardDeque = ParticipantCards.createCardDeque()
    val participants = participantName.participantsFromNames(cardDeque)
    OutputView.printParticipantsCard(participants)

    // PHASE 3
    val result = participants.map { participant ->
        var resultText = ""
        var temporary = participant
        while (true) {
            val blackJack = BlackJack(cardDeque, temporary)
            val tempParticipant = blackJack.doBlackJack(
                printGetOneMoreCard = { OutputView.printGetOneMoreCard(it) },
                input = { InputView.inputGetMoreCard() },
                printNewCard = { OutputView.printNewCards(it) }
            ).also {
                resultText = ParticipantPrinter(it).print()
                temporary = it
            }
            if (tempParticipant.participantState !is ParticipantState.HIT) { break }
        }
        resultText
    }
    OutputView.printResult(result)
}
