package blackjack.view

import blackjack.model.player.Player

object OutputView {
    fun firstTurn(players: List<Player>) {
        val name = players.joinToString(separator = ",") { it.name }

        println("${name}에게 2장의 카드를 나누었습니다.")
    }

    fun drawCard(player: Player) {
        val cards = player.getDisplayCards()

        println("${player.name}카드: $cards")
    }

    fun printPoint(players: List<Player>) {
        for (player in players) {
            val cards = player.getDisplayCards()
            val point = player.getTotalPointForBlackJack()

            println("${player.name}카드: $cards - 결과: $point")
        }
    }
}
