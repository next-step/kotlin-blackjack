package blackjack.view

import blackjack.business.participants.Player

class SimplePlayerOutputHandler : PlayerOutputHandler {
    override fun print(player: Player) = println("${player.name}카드: ${player.cards.joinToString(", ")}")
}
