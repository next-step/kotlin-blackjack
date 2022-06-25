package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Player

object PlayerCardsWriter {

    fun write(player: Player) {
        print("${player.name}카드: ")

        val playerCards = player.hand.cards.joinToString(",") { "{${it.number.value}:${it.suit}}" }
        println(playerCards)
    }
}
