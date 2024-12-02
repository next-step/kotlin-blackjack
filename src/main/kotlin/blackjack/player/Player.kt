package blackjack.player

import blackjack.card.Card
import blackjack.machine.BlackJackMachine

class Player(
    val name: String,
    val hand: Hand = Hand(cards = emptyList()),
) {
    fun isBust(): Boolean = hand.sum() > BlackJackMachine.BLACKJACK

    fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card))

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
