package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.PlayerStatus

object GameResultPrinter {
    fun print(player: Player) {
        val playerName: PlayerName = player.name
        val cardMessages = player.hand.cards.joinToString(
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
}
