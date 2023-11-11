package blackjack.view

import blackjack.domain.Player

object OutputView {

    fun writePlayers(players: List<Player>) {
        println("딜러와 ${players.joinToString(", ") { it.name.value }}에게 2장의 나누었습니다.")
    }
}
