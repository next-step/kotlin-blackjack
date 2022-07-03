package blackjack.view

import blackjack.domain.gameresult.GameResult
import blackjack.domain.gameresult.GameResults
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants

object OutputView {
    fun printPlayersInitCards(participants: Participants) {
        val playerNames = participants.participants.joinToString(", ") { it.name }
        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        participants.participants.forEach { printPlayerNameAndCards(it) }
        println()
    }

    fun printCurrentPlayerCards(participant: Participant) {
        printPlayerNameAndCards(participant)
        println()
    }

    private fun printPlayerNameAndCards(participant: Participant) {
        val cardNames = participant.openInitCards().joinToString(", ") { "${it.face.value}${it.suit.value}" }
        println("${participant.name}카드: $cardNames")
    }

    fun printAllParticipantsCard(participants: Participants) {
        participants.participants.forEach {
            printPlayerResult(it)
        }
        println()
    }

    private fun printPlayerResult(participant: Participant) {
        val cardNames = participant.cards.value.joinToString(", ") { "${it.face.value}${it.suit.value}" }
        println("${participant.name}카드: $cardNames - 결과: ${participant.score}")
    }

    fun printDealerTurn() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다. \n")
    }

    fun printResult(results: List<GameResults>) {
        println("## 최종 승패")
        results.forEach { println("${it.name}: ${printDetailResult(it.result)}") }
    }

    private fun printDetailResult(gameResult: Map<GameResult, Int>): String {
        return gameResult.keys.joinToString { "${gameResult[it]}${it.value} " }
    }
}
