package blackjack.view

import blackjack.domain.game.Game.Companion.FIRST_DRAW_NUMBER
import blackjack.domain.player.Player

object PlayerView {
    fun printInitialHand(players: List<Player>) {
        println()
        println("${players.joinToString { it.name }} 에게 $FIRST_DRAW_NUMBER 장의 카드를 나누었습니다.")
        players.forEach { printPlayerInfo(it) }
        println()
    }

    fun printPlayerInfo(player: Player) {
        println(parsePlayerInfoToString(player))
    }

    fun parsePlayerInfoToString(player: Player): String {
        val cards = player.hand.cards
        return "${player.name} 카드: ${cards.joinToString { "[${it.suit.displayName}]${it.symbol.displayName}" }}"
    }
}
