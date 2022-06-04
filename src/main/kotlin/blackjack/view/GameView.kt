package blackjack.view

import blackjack.domain.Participant
import blackjack.domain.Participants

object GameView {
    fun giveCard(participants: Participants) {
        participants.players.joinToString { it.name }.also {
            println("${it}에게 2장을 나눠주었습니다.")
        }
    }

    fun displayInitialCard(participants: Participants) {
        participants.players.forEach {
            println("${it.name}카드 : ${it.getCardDisplayName()}")
        }
    }

    fun displayPlayerCard(participant: Participant) {
        participant.also {
            println("${it.name}카드 : ${it.getCardDisplayName()}")
        }
    }

}