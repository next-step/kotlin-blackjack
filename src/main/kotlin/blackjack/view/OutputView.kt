package blackjack.view

import blackjack.domain.Player

object OutputView {
    fun printCardState(players: List<Player>) {
        println("${players.joinToString(separator = ", ") { it.name.value }}에게 2장의 카드를 나누었습니다.")
        players.forEach {
            val joinToString =
                it.cards.joinToString(separator = ", ") { card -> "${card.character.displayName}${card.shape.displayName}" }
            println("${it.name}카드: $joinToString")
        }
    }
}
