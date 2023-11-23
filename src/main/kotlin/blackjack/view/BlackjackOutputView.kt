package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Participant
import blackjack.domain.Suit

object BlackjackOutputView {
    fun printInitialCards(participants: List<Participant>) {
        println("\n${participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

        participants.forEach { printCards(it) }
        println()
    }

    fun printCards(participant: Participant) {
        println("${participant.name} 카드: ${getCardsString(participant)}")
    }

    fun printResult(participants: List<Participant>) {
        println()

        participants.forEach {
            println("${it.name} 카드: ${getCardsString(it)} - 결과: ${it.cards.calculateScore()}")
        }
    }

    private fun getCardsString(participant: Participant): String {
        return participant.cards
            .get()
            .joinToString { it.toOutputString() }
    }

    private fun Card.toOutputString(): String {
        return "${this.denomination.alias}${this.suit.toOutputString()}"
    }

    private fun Suit.toOutputString(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }
}
