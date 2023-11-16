package blackjack.view

import blackjack.business.Player

class DetailedPlayerOutputHandler : PlayerOutputHandler {
    override fun print(player: Player) =
        println("${player.name}카드: ${player.cards.joinToString(", ")} - 결과: ${player.score}")
}
