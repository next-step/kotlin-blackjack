package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun printUserCardList(player: Player) {
        val cards = player.getCards().joinToString(", ")
        println("${player.getName()}카드: $cards")
    }

    fun printUserCardListWithResult(player: Player) {
        val cards = player.getCards().joinToString(", ")
        val result = player.getCards().sumOf { it.cardSymbol.score }
        println("${player.getName()}카드: $cards - 결과: $result")
    }
}
