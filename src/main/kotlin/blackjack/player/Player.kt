package blackjack.player

import blackjack.card.Card
import blackjack.machine.BlackJackMachine

class Player(
    val name: String,
    val cards: List<Card>,
) {
    fun isHitCard(): Boolean = this.sumOfCard() < BlackJackMachine.BLACKJACK

    fun hitCard(): Player = Player(name = name, cards = cards + Card.random())

    private fun sumOfCard(): Int = cards.sumOf { it.rank.value }

    companion object {
        fun from(name: String): Player = Player(name, cards = List(size = 2) { Card.random() })
    }
}
