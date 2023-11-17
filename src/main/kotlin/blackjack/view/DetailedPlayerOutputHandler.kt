package blackjack.view

import blackjack.business.participants.Player

object DetailedPlayerOutputHandler {
    fun print(player: Player) =
        println("${player.name}카드: ${player.cards.joinToString(", ")} - 결과: ${player.score}")
}
