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

        if (temporary.participantState is ParticipantState.BLACKJACK) {
            resultText = ParticipantPrinter.print(participant)
            return
        }

        while (true) {
            val blackJack = BlackJack(cardDeque, temporary)

            if (temporary.participantState !is ParticipantState.HIT) break
            OutputView.printGetOneMoreCard(participantName = participant.name)
            val input = participant.askWantToGetOneMoreCard { InputView.inputGetMoreCard() }.not()
            // 여기 이후로 스탠드일 수 있음

            if (participant.participantState !is ParticipantState.HIT) {
                resultText = ParticipantPrinter.print(temporary)
                break
            }
            blackJack.doBlackJack(input)
                .also {
                    OutputView.printNewCards(it)
                    resultText = ParticipantPrinter.print(it)
                    temporary = it
                }
        }
        resultText
    }
    OutputView.printResult(result)
}
