package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun printDistributionPlan(players: List<Player>, count: Int) {
        val names = players.joinToString(", ") { it.getName() }
        println("${names}에게 ${count}장의 카드를 나누어 주었습니다.")
    }

    fun printUserCardList(player: Player) {
        val cards = player.getCards().joinToString(", ") { it.show() }
        println("${player.getName()}카드: $cards")
    }

    fun printUserCardListWithResult(player: Player) {
        val cards = player.getCards().joinToString(", ") { it.show() }
        val result = player.getCards().sumOf { it.cardSymbol.score }
        println("${player.getName()}카드: $cards - 결과: $result")
    }
}
