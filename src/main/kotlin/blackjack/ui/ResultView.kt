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
            println()
        }
    }

    fun showStatusOfParticipant(participant: Participant, useNewLine: Boolean = true) {
        print("${participant.name}카드 : ")
        showCards(participant)
        if (useNewLine) println()
    }

    private fun showCards(participant: Participant) {
        participant.cards.forEach {
            val postfix = if (it == participant.cards.last()) "" else ", "
            print("${it.info.displayName}${it.type.displayName}$postfix")
        }
    }

    fun showGameResult(participants: List<Participant>) {
        println()
        participants.forEach { participant ->
            showStatusOfParticipant(participant, useNewLine = false)
            println(" - 결과 : ${participant.takeBestScore()}")
        }
    }
}
