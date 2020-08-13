package blackJack.view

import blackJack.domain.Player

object ResultView {
    fun resultReady(players: List<Player>) {
        println("${players.joinToString { it.name!! }}에게 2장의 카드를 주었습니다.")
        players.forEach { resultPlayerHands(it) }
        println()
    }

    fun resultPlayerHands(player: Player, result: String = "") {
        println("${player.name}카드: ${player.hands.joinToString { it.name }} $result")
    }

    fun resultGame(players: List<Player>) {
        println("----------------")
        players.forEach {
            resultPlayerHands(it, "- 결과: ${it.totalScore}")
        }
    }

    fun resultWhetherBust(player: Player) {
        if (player.isBust()) {
            resultPlayerHands(player)
            println("${player.name}님은 버스트 했습니다")
        } else {
            resultPlayerHands(player)
            println("${player.name}님은 버스트 하지 않았습니다.")
        }
    }
}
