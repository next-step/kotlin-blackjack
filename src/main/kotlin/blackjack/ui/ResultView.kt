package blackjack.ui

import blackjack.controller.BlackJackGame
import blackjack.model.Participant

object ResultView {

    fun showInitialStatusOfParticipants(participants: List<Participant>) {
        participants.forEach { participant ->
            val separator = if (participant == participants.first()) "" else ", "
            print("$separator${participant.name}")
        }
        println("에게 ${BlackJackGame.DEFAULT_CARD_COUNTS}장의 카드를 나눠주었습니다.")

        participants.forEach { participant ->
            print("${participant.name}카드 : ")
            showCards(participant)
        }
    }

    private fun showCards(participant: Participant) {
        participant.cards.forEach {
            val postfix = if (it == participant.cards.last()) "\n" else ", "
            print("${it.info.displayName}${it.type.displayName}$postfix")
        }
    }
}
