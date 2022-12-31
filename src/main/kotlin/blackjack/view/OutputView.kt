package blackjack.view

import blackjack.domain.Participant

object OutputView {
    fun printInitialCards(players: List<Participant>) {
        println("${players.joinToString(", ") { it.name }}에게 2장을 나누어 주었습니다.")
        players.forEach { player ->
            println("${player.name}: ${player.cards.cards.joinToString(", ") { it.toString() }}")
        }
    }
}
