package blackjack.view

import blackjack.domain.Hands
import blackjack.domain.Player

object ResultView {
    fun printInit(players: List<Player>) {
        println()
        println("${players.joinToString(", ") { it.name }}에게 ${Hands.INIT_CARD_SIZE}장의 카드를 나누었습니다.")
        players.forEach { printPlayerInfo(it) }
        println()
    }

    fun printPlayerInfo(player: Player) {
        println(playerInfo(player))

        if (player.isBust()) {
            println("${player.name} Bust!!")
        }
    }

    fun printResult(players: List<Player>) {
        println()
        return players.forEach { printPlayerResult(it) }
    }

    private fun printPlayerResult(player: Player) {
        println("${playerInfo(player)} - 결과: ${player.hands.sum()}")
    }

    private fun playerInfo(player: Player): String {
        return "${player.name}카드: ${player.hands.cards.joinToString(",") { "${it.symbol}_${it.type}" }}"
    }
}
