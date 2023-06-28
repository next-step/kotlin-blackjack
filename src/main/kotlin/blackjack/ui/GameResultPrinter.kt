package blackjack.ui

import blackjack.domain.Player

object GameResultPrinter {
    fun print(player: Player) {
        println("${printCard(player)} - 결과: ${player.bestHandTotal()}")
    }

    fun printCard(player: Player): String {
        val cardMessages = player.hand.cards.joinToString(
            separator = ", ",
            transform = { "${it.cardNumber.displayName}${it.cardShape.displayName}" })
        return "${player.name}카드: $cardMessages"
    }
}
