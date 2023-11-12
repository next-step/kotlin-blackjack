package blackjack.view

import blackjack.business.Player

object OutputHandler {
    fun printPlayer(player: Player) = println("${player.name}카드: ${player.cards.joinToString(", ")}")
}
