package blackjack.view

import blackjack.domain.DrawResult
import blackjack.domain.GameResult

private const val PLAYER_NAME_DELIMITER = ", "

fun printFirstDrawResult(drawResults: List<DrawResult>) {
    println("${parsePlayerNames(drawResults)}에게 2장의 카드를 나누었습니다.")
    drawResults.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(drawResult: DrawResult) {
    println("${drawResult.playerName}카드: ${drawResult.cards}")
}

fun printGameResults(gameResults: List<GameResult>) {
    gameResults.forEach { printGameResult(it) }
}

private fun parsePlayerNames(drawResults: List<DrawResult>) =
    drawResults.joinToString(PLAYER_NAME_DELIMITER) { it.playerName }

private fun printPlayerDrawResult(drawResult: DrawResult) {
    println("${drawResult.playerName}카드: ${drawResult.cards}}")
}

private fun printGameResult(gameResult: GameResult) {
    println("${gameResult.playerName}카드: ${gameResult.cards} - 결과: ${gameResult.score}")
}
