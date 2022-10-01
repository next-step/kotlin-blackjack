package blackjack.view

import blackjack.domain.gameresult.GameResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Players

object OutputView {
    fun printPlayersInitCards(players: Players) {
        val playerNames = players.players.joinToString(", ") { it.name }
        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        players.players.forEach { printPlayerNameAndCards(it) }
        println()
    }

    fun printCurrentPlayerCards(participant: Participant) {
        printPlayerNameAndCards(participant)
        println()
    }

    private fun printPlayerNameAndCards(participant: Participant) {
        val cardNames = participant.cards.value.joinToString(", ") { "${it.face.value}${it.suit.value}" }
        println("${participant.name}카드: $cardNames")
    }

    fun printAllParticipantsCard(players: Players, dealer: Dealer) {
        players.players.forEach {
            printPlayerResult(it)
        }
        printPlayerResult(dealer)
        println()
    }

    private fun printPlayerResult(participant: Participant) {
        val cardNames = participant.cards.value.joinToString(", ") { "${it.face.value}${it.suit.value}" }
        println("${participant.name}카드: $cardNames - 결과: ${participant.score}")
    }

    fun printDealerTurn() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다. \n")
    }

    fun printResult(results: List<GameResult>) {
        println("## 최종 수익")
        results.forEach { println("${it.name}: ${it.sumGameProfits().value.toInt()}") }
    }
}
