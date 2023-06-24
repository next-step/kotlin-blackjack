package blakjack.view

import blakjack.domain.Player

object OutputView {
    fun printInitialPlayerCards(players: List<Player>) {
        println()
        println("${players.joinToString { it.name }}에게 2장의 나누었습니다.")
        players.forEach(this::printPlayerCards)
    }

    fun printPlayerCards(player: Player) {
        println(getPrintPlayerCards(player))
    }

    fun printPlayerCardsWithScore(players: List<Player>) {
        players.forEach {
            println("${getPrintPlayerCards(it)} - 결과: ${it.score}")
        }
    }

    private fun getPrintPlayerCards(player: Player): String {
        return "${player.name}카드: ${player.cards.values.joinToString(",") { it.korean }}"
    }
}
