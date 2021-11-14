package blackjack.view

import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players

class OutputView {

    companion object {
        private const val PRINT_START_GAME = "에게 2장의 카드를 나누었습니다."

        fun printStartGame(players: Players) {
            val playerNames = players.value.joinToString { it.name }
            println("${playerNames}$PRINT_START_GAME")

            for (player in players.value) {
                printPlayerCard(player)
            }
        }

        fun printPlayerCard(player: Player) {
            println("${player.name}카드: ${player.haveCards()}")
        }
    }
}
