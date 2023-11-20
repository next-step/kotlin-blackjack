package blackjack_dealer.ui

import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.printer.ParticipantPrinter

object OutputView {
    fun enterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printParticipantInformation(participants: Participants) {
        participants.forEach { participant ->
            println(
                ParticipantPrinter.print(participant)
            )
        }
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
}
