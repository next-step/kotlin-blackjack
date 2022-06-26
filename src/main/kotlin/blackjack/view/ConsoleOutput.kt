package blackjack.view

import blackjack.domain.card.Hand
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

class ConsoleOutput : Output {
    override fun printInitialStatus(participants: Participants) {
        val names = participants.joinToString(", ") { it.name }
        println("${names}에게 카드를 2장 나누어주었습니다")
        participants.forEach { printStatus(it) }
    }

    override fun printStatus(participant: Participant) {
        println("${participant.name}카드: ${convertHandToString(participant.hand)}")
    }

    override fun printResult(participants: Participants) {
        println()
        participants.forEach { println("${it.name}카드: ${convertHandToString(it.hand)} - 결과: ${it.hand.getScore()}") }
    }

    private fun convertHandToString(hand: Hand) = hand.value.joinToString(",") { it.rank.symbol + it.suit.symbol }
}
