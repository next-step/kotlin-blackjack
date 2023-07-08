package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun playerAndCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }
}
