package view

import model.*

object OutputView {
    fun printFirstCard(players: Players) {
        println("딜러와 ${players.players.joinToString(", ", transform = Player::name)}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardStatusOnFirstRound(participant: Participant) {
        println("${participant.name}카드: ${participant.getPublicCardsOnFirstRound()}")
    }

    fun printCardStatus(participant: Participant) {
        println("${participant.name}카드: ${participant.cards}")
    }

    fun printDealerMustGetCard() {
        println("딜러는 ${BlackJackConstants.DEALER_DRAW_THRESHOLD}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printRoundResult(participant: Participant) {
        println("${participant.name}카드: ${participant.cards} - 결과: ${participant.calculateScore()}")
    }

    fun printFinalResult(gameResult: GameResult) {
        println("## 최종 승패")
        println("딜러: ${gameResult.losers.size} 승 ${gameResult.winners.size} 패 ${gameResult.draws.size} 무")
        gameResult.winners.forEach { println("${it.name}: 승") }
        gameResult.losers.forEach { println("${it.name}: 패") }
        gameResult.draws.forEach { println("${it.name}: 무") }
    }
}
