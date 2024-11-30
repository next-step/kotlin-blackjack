package blackjack.view

import blackjack.player.Player

object ResultView {
    fun printPlayerName(
        player1: Player,
        player2: Player,
    ) {
        println("${player1.name}, ${player2.name}에게 2장을 나누었습니다.")
    }

    fun printPlayersCardStatus(players: List<Player>) {
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString(", ") { "${it.rank.value}${it.suit.description}" }}")
        }
    }
}
