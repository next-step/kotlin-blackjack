package blackjack_dealer.ui

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.printer.DealerPrinter
import blackjack_dealer.ui.printer.ParticipantPrinter
import blackjack_dealer.ui.printer.ParticipantResultPrinter

object OutputView {
    fun enterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printDivideCardsToGamer(dealer: Dealer, participants: Participants) {
        val participantsName = participants.joinToString { it.getParticipantName() }
        println(DIVIDE_CARDS_TO_GAMERS.format(dealer.getDealerName(), participantsName))
    }


    fun printGamersInformation(dealer: Dealer, participants: Participants) {
        println(DealerPrinter.print(dealer))
        participants.forEach { participant ->
            printParticipantInformation(participant)
        }
        println()
    }

    fun printParticipantInformation(participant: Participant) {
        println(
            ParticipantPrinter.print(participant)
        )
    }

    fun askGetOneMoreCard(participant: Participant) {
        println(ASK_GET_MORE_ONE_CARD.format(participant.getParticipantName()))
    }

    fun printResult(participants: Participants) {
        println()
        participants.forEach { participant ->
            println(ParticipantResultPrinter.print(participant))
        }
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DIVIDE_CARDS_TO_GAMERS = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val ASK_GET_MORE_ONE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
