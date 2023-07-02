package blackjack.view

import blackjack.domain.Player

object OutputView {
    fun printStart(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누었습니다.")
        for (player in players) {
            printPlayerCard(player)
        }
    }

    fun printPlayerCard(player: Player) {
        println(resultMessage(player))
    }

    fun printPlayerResult(player: Player) {
        println("${resultMessage(player)}- 결과: ${player.score().value}")
    }

    private fun resultMessage(player: Player): String {
        val cardsMessage = player.cards().joinToString(", ") { it.numberName() + it.shapeName() }
        return "${player.name}의 카드: $cardsMessage"
    }
}
