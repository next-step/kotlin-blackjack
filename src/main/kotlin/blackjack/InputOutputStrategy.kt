package blackjack

import view.Input
import view.Output

data class InputOutputStrategy(
    val getLine: () -> String = Input::getLine,
    val printDealerDealing: () -> Unit = { Output.printAny(Message.PRINT_DEALER_DEALING) },
    val printParticipantCards: (GameParticipant) -> Unit = Output::printParticipantCards,
    val printParticipantAction: (GameParticipant) -> Unit = Output::printParticipantAction
)
