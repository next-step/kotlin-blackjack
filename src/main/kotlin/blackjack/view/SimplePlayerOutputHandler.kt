package blackjack.view

import blackjack.business.participants.Player

object SimplePlayerOutputHandler {
    fun print(player: Player) = println("${player.name}카드: ${player.cards.joinToString(", ")}")
}
