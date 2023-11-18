import blackjack.domain.BlackJack
import blackjack.entity.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import blackjack.entity.ParticipantState
import blackjack.entity.participantsFromNames
import blackjack.entity.toCards
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.print

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val cardDeque = Cards.createCardDeque()
    val participants = participantName.participantsFromNames(cardDeque)
    OutputView.printParticipantsCard(participants)

    // PHASE 3
    val blackJack = BlackJack(cardDeque)
    val result = participants.map { participant ->
        var resultText = ""
        var temporary = participant
        while (true) {
            val tempParticipant = blackJack.doBlackJack(
                participant = temporary,
                printGetOneMoreCard = { OutputView.printGetOneMoreCard(it) },
                input = { InputView.inputGetMoreCard() },
                printNewCard = { OutputView.printNewCards(it) }
            ).also {
                resultText = it.print()
                temporary = it
            }
            if (tempParticipant.participantState !is ParticipantState.HIT) { break }
        }
        resultText
    }
    OutputView.printResult(result)
}
