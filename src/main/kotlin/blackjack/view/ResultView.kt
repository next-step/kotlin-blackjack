package blackjack.view

import blackjack.domain.GameTable.Companion.INIT_CARD_DRAW_COUNT
import blackjack.domain.Participant

object ResultView {
    fun printInitCardReceive(participants: List<Participant>) {
        val players = Participant.separate(participants).first
        println("딜러와 ${players.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")
    }

    fun printParticipantsCard(
        participants: List<Participant>,
        printScore: Boolean,
    ) {
        val (players, dealer) = Participant.separate(participants)
        printParticipantCard(participant = dealer, printScore = printScore)
        players.forEach { printParticipantCard(participant = it, printScore = printScore) }
    }

    fun printParticipantCard(
        participant: Participant,
        printScore: Boolean,
    ) {
        val cards = participant.cards.values.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        val scoreText = "- 결과: ${participant.cards.score}"
        println("${participant.name} 카드: $cards ${if (printScore) scoreText else ""}")
    }

    fun printDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun linebreak() {
        println()
    }
}
