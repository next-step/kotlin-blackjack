package blackjack_dealer.ui

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.printer.ParticipantPrinter

object OutputView {
    fun enterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printParticipantsInformation(participants: Participants) {
        participants.forEach { participant ->
            printParticipantInformation(participant)
        }
    }

    fun printParticipantInformation(participant: Participant) {
        println(
            ParticipantPrinter.print(participant)
        )
    }

    fun askGetOneMoreCard(participant: Participant) {
        println(ASK_GET_MORE_ONE_CARD.format(participant.getParticipantName()))
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ASK_GET_MORE_ONE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
