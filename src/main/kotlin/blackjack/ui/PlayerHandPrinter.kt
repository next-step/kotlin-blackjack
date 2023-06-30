package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.PlayerName

object PlayerHandPrinter {
    fun print(player: Player) {
        val playerName: PlayerName = player.name
        val cardMessages = player.hand.cards.joinToString(
            separator = ", ",
            transform = { "${it.cardNumber.displayName}${it.cardSuit.displayName}" }
        )
        println("${playerName.value}카드: $cardMessages")
    }

}
