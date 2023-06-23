package blackjack.view

import blackjack.domain.Player

object OutputView {
    fun roundBeginNotice(players: Array<Player>) {
        val playerNames = players.joinToString { it.name }
        println("\n${playerNames}에게 2장의 나누었습니다.")
        players.forEach(::handNotice)
        println()
    }

    fun handNotice(player: Player) {
        val showHands = player.showHands()
        println("${player.name}카드: $showHands")
    }

    fun roundResultNotice(players: Array<Player>) {
        println()
        players.forEach {
            val showHands = it.showHands()
            val optimalValue = it.optimalValue()
            println("${it.name}카드: $showHands - 결과: $optimalValue")
        }
    }
}
