package blackjack.view

import blackjack.domain.score.PlayerScore
import blackjack.view.CardView.cardsToString

class ResultView {

    fun players(players: List<String>) {
        println("딜러와 ${players.joinToString(", ")} 에게 2장의 나누었습니다.")
    }

    fun playerScore(playerScores: List<PlayerScore>) {
        playerScores.forEach {
            playerCard(it)
        }
    }

    fun dealerScore(dealerScore: PlayerScore) {
        println()
        playerCard(dealerScore)
    }

    private fun playerCard(playerScore: PlayerScore) {
        println("${playerScore.player.name}카드: ${cardsToString(playerScore.player.cards).joinToString(", ")} - 결과: ${playerScore.score}")
    }
}
