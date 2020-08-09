package blackjack.view

import blackjack.domain.Card

fun notifyStartGame(playerNames: List<String>) {
    println("${playerNames.joinToString(", ")}에게 2장의 카드를 나누어 주었습니다.")
}

fun printPlayerCards(playerName: String, cards: List<Card>) {
    println("${playerName}카드: ${cards.joinToString(", ")}")
}

fun printResult(playerName: String, cards: List<Card>, score: Int) {
    println("${playerName}카드: ${cards.joinToString(", ")} - 결과: $score")
}
