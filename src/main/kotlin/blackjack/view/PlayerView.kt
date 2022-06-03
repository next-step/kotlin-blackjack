package blackjack.view

import blackjack.domain.player.Player
import blackjack.util.FIRST_DRAW_NUMBER

object PlayerView {
    fun printFirstTurn(players: List<Player>) {
        println()
        println("${players.joinToString { it.name }} 에게 $FIRST_DRAW_NUMBER 장의 카드를 나누었습니다.")
        println(players.joinToString("\n"))
        println()
    }
}
