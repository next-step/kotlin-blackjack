package views

import domains.Cards

object Output {
    fun printFirstDrawCards(playerNames: List<String>, drawCount: Int) {
        println("${playerNames.joinToString { it }} 에게 $drawCount 장을 나누었습니다.")
    }

    fun printHasCards(playerName: String, cards: Cards) {
        println("$playerName 카드: $cards")
    }

    fun printResult(playerName: String, cards: Cards, summed: Int) {
        println("$playerName 카드: $cards - 결과: $summed")
    }
}
