package blackjack.view

import blackjack.domain.Hands
import blackjack.domain.GameResult

private const val PLAYER_NAME_DELIMITER = ", "

fun printFirstDrawResult(hands: List<Hands>) {
    println("${parsePlayerNames(hands)}에게 2장의 카드를 나누었습니다.")
    hands.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(hands: Hands) {
    println("${hands.playerName}카드: ${hands.cards}")
}

fun printGameResults(gameResults: List<GameResult>) {
    gameResults.forEach { printGameResult(it) }
}

private fun parsePlayerNames(hands: List<Hands>) =
    hands.joinToString(PLAYER_NAME_DELIMITER) { it.playerName }

private fun printPlayerDrawResult(hands: Hands) {
    println("${hands.playerName}카드: ${hands.cards}}")
}

private fun printGameResult(gameResult: GameResult) {
    println("${gameResult.playerName}카드: ${gameResult.cards} - 결과: ${gameResult.score}")
}
