package blackjack.ui

import blackjack.domain.Player

object PlayerHandPrinter {
    fun print(player: Player) {
        println(message(player))
    }

    fun message(player: Player): String {
        val cardMessages = player.hand.cards.joinToString(
            separator = ", ",
            transform = { "${it.cardNumber.displayName}${it.cardShape.displayName}" }
        )
        return "${player.name}카드: $cardMessages"
    }
}
