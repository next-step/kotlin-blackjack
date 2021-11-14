package blackjack.view

import blackjack.application.BlackjackGame
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

        fun printBlackjackResult(blackjackGames: List<BlackjackGame>) {
            println()
            for (blackjackGame in blackjackGames) {
                val player = blackjackGame.player
                val blackjackResult = getBlackjackResult(blackjackGame)
                println("${player.name}카드: ${player.haveCards()} - 결과: $blackjackResult")
            }
        }

        private fun getBlackjackResult(blackjackGame: BlackjackGame): String {
            val totalScore = blackjackGame.player.cards.getTotalScore()
            return if (totalScore >= 21) {
                blackjackGame.state.toString()
            } else {
                totalScore.toString()
            }
        }
    }
}
