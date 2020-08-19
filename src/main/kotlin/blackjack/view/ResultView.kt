package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Participants

object ResultView {
    fun printPlayersCard(players: Participants) {
        println("${players}에게 2장의 카드를 나누었습니다.")
        players.players.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString()}")
    }

    fun printResult(players: Participants) {
        players.players.forEach {
            println("${it.name}카드: ${it.cards.joinToString()} - 결과: ${it.total}")
        }
    }
}
