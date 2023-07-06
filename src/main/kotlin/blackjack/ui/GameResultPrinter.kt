package blackjack.ui

import blackjack.domain.GameResult
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerStatus

object GameResultPrinter {
    fun ofPlayer(player: Player) {
        val playerName: PlayerName = player.name
        val cardMessages = player.hand.getCards().joinToString(
            separator = ", ",
            transform = { "${it.cardNumber.displayName}${it.cardSuit.displayName}" }
        )

        val result = when (player.status) {
            PlayerStatus.BUST -> "BUST"
            PlayerStatus.BLACKJACK -> "BLACKJACK"
            else -> player.total()
        }

        println("${playerName.value}카드: $cardMessages - 결과: $result")
    }

    fun summary(gameResult: GameResult) {
        val (dealerResult, playerResults) = gameResult
        println("## 최종 승패")
        println("딜러: ${dealerResult.win}승 ${dealerResult.lose}패")
        playerResults.forEach {
            println("${it.player.name.value}: ${it.result.displayName}")
        }
    }
}
