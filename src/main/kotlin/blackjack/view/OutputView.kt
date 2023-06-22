package blackjack.view

import blackjack.domain.Player

object OutputView {
    fun roundStartNotice(players: Array<Player>) {
        val playerNames = players.joinToString { it.name }
        println("\n${playerNames}에게 2장의 나누었습니다.")
    }

    fun handNotice(player: Player) {
        val showHands = player.showHands()
        println("${player.name}카드: $showHands")
    }
}
