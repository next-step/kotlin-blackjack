package blackjack.view

import blackjack.domain.DrawResult

private const val PLAYER_NAME_DELIMITER = ", "

fun printFirstDrawResult(drawResults: List<DrawResult>) {
    println("${parsePlayerNames(drawResults)}에게 2장의 카드를 나누었습니다.")
    drawResults.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(drawResult: DrawResult) {
    println("${drawResult.playerName}카드: ${drawResult.cards}")
}

private fun parsePlayerNames(drawResults: List<DrawResult>) =
    drawResults.joinToString(PLAYER_NAME_DELIMITER) { it.playerName }

private fun printPlayerDrawResult(drawResult: DrawResult) {
    println("${drawResult.playerName}카드: ${drawResult.cards}}")
}

