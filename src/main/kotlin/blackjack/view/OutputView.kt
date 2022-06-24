package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {
    fun printAllInitCards(players: Players) {
        val playerNames = players.players.joinToString(", ") { it.name }
        println("${playerNames}에게 2장의 카드를 나누었습니다.")
        players.players.forEach { printInitCards(it) }
    }

    private fun printInitCards(player: Player) {
        val cardNames = player.cards.value.joinToString(", ") { "${it.face.value}${it.suit.value}" }
        println("${player.name}카드: $cardNames")
    }
}
