package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun playerAndCards(player: Player) {
        println("${player.name}카드: ${player.cards.cards.joinToString(", ")}")
    }

    fun initialCards(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        players.forEach {
            playerAndCards(it)
        }
    }

    fun result(players: List<Player>) {
        players.forEach {
            println("${it.name}카드: ${it.cards.cards.joinToString(", ")} - 결과: ${it.cards.calculateScore()}")
        }
    }
}
