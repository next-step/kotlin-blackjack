package blackjack.view

import blackjack.domain.Player

object OutputView {

    fun writePlayerNames(players: List<Player>) {
        println("딜러와 ${players.joinToString(", ") { it.name.value }}에게 2장의 나누었습니다.")
    }

    fun writePlayerCards(vararg players: Player) {
        players.forEach {
            println(
                "${it.name.value}카드: ${it.cards.joinToString(", ") { card -> "${card.rank.score}${card.suit.symbol}" }}"
            )
        }
    }
}
