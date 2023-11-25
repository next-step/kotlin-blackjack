package blackjack.view

import blackjack.domain.player.Participant

object ConsoleResult {
    fun drawAllFirstTwoCards(participants: List<Participant>) {
        println()
        println("${participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsOfPlayers(participants: List<Participant>) {
        participants.forEach { printCardsOfPlayer(it) }
    }

    fun printCardsOfPlayer(participant: Participant) {
        println("${participant.name}카드: ${participant.cards.joinToString { card -> card.character.mark + card.shape.korean }}")
    }

    fun printCardsAndTotalScoreOfPlayers(participants: List<Participant>) {
        println()
        participants.forEach {
            println("${it.name}카드: ${it.cards.joinToString { card -> card.character.mark + card.shape.korean }} - 결과: ${it.totalScore}")
        }
    }

    fun notifyPlayerCannotDraw(participant: Participant) {
        println("${participant.name}는 총 점수가 21점이 넘어 더이상 카드를 받을 수 없습니다.")
    }
}
