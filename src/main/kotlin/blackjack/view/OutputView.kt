package blackjack.view

import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players

class OutputView {

    companion object {
        private const val PRINT_START_GAME = "에게 2장의 카드를 나누었습니다."
        private const val BLACKJACK_END_NUMBER = 21

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

        fun printBlackjackResult(players: List<Player>) {
            println()
            for (player in players) {
                val blackjackResult = getBlackjackResult(player)
                println("${player.name}카드: ${player.haveCards()} - 결과: $blackjackResult")
            }
        }

        private fun getBlackjackResult(player: Player): String {
            val totalScore = player.cards.getTotalScore()
            return if (totalScore >= BLACKJACK_END_NUMBER) {
                player.state.toString()
            } else {
                totalScore.toString()
            }
        }
    }
}
