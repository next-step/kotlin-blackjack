package blackjack.view

import blackjack.model.player.Player

class OutputView : Dashboard {
    override fun printPlayerInitStatus(players: List<Player>) {
        val names = players.joinToString { it.name }
        println("\n${names}에게 2장의 카드를 나누었습니다.")
        players.forEach { player ->
            val card = player.cards.joinToString(separator = ", ") { "${it.number.score}${it.suit.name}" }
            println("${player.name} 카드 : $card")
        }
    }
}
