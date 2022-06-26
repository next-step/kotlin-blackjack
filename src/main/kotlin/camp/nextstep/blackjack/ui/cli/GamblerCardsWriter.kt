package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Gambler

object GamblerCardsWriter {

    fun write(gambler: Gambler) {
        print("${gambler.name}카드: ")

        val gamblerCards = gambler.hand.cards.map { it.card }.joinToString(",") { "{${it.number.value}:${it.suit}}" }
        println(gamblerCards)
    }
}
