package blackjack.ui

import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player

object OutputView {
    fun printAllPlayerCards(participants: List<Participant>) {
        val names = participants.joinToString { it.name }
        println("$names 에게 2장의 카드를 나누었습니다.")
        for(participant in participants) {
            println("${participant.name} 카드 : ${participant.showCards()}")
        }
    }

    fun printPlayerCards(participant: Participant) {
        println("${participant.name} 의 카드 : ${participant.showCards()}")
    }

    fun printResult(participants: List<Participant>) {
        println("\n ----------- 결산 -----------")
        for (participant in participants) {
            println("${participant.name} 카드 : ${participant.showCards()} - 합계 ${participant.getScore()}")
        }
    }
}