package blackjack.player

import blackjack.card.Card
import blackjack.machine.BlackJackMachine

open class Player(
    val name: String,
    val hand: Hand = Hand(cards = emptyList()),
) {
    fun isBust(): Boolean = hand.sum() > BlackJackMachine.BLACKJACK

    open fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card))

    fun convertToPlayers() = Players(players = listOf(this))

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
