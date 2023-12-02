package blackjack.view

import blackjack.domain.player.Player

object OutputView {
    fun printCardState(players: List<Player>) {
        println("${players.joinToString(separator = ", ") { it.name.value }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printCurrentCardState(it) }
    }

    fun printCurrentCardState(player: Player) {
        val joinToString =
            player.cards.joinToString(separator = ", ") { card -> "${card.character.displayName}${card.shape.displayName}" }
        println("${player.name}카드: $joinToString")
    }
}
