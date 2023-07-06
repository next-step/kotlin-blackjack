package blackjack.view

import blackjack.Player
import blackjack.Players

class ResultView {
    fun outputInitialHand(players: Players) {
        println("${players.joinToString { it.name }}에게 2장씩 나누었습니다.")
        players.forEach { player ->
            println(getCurrentHandString(player))
        }
    }

    fun outputCurrentHand(player: Player) {
        println(getCurrentHandString(player))
    }

    fun outputGameResult(players: Players) {
        println()
        players.forEach { player ->
            println("${getCurrentHandString(player)} - 결과: ${player.point}")
        }
    }

    private fun getCurrentHandString(player: Player): String {
        return "${player.name}카드: ${player.deck.cards.joinToString { it.toString() }}"
    }
}
