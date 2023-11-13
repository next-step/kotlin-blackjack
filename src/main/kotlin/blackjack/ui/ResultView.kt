package blackjack.ui

import blackjack.model.Participant

object ResultView {

    fun showInitialStatusOfParticipants(participants: List<Participant>) {
        participants.forEach { participant ->
            val separator = if (participant == participants.first()) "" else ", "
            print("$separator${participant.name}")
        }
        println("에게 2장의 카드를 나눠주었습니다.")
    }
}
