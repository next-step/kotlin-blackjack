package blackjack.player

import blackjack.card.Card
import blackjack.card.Cards
import blackjack.machine.BlackJackMachine

class Player(
    val name: String,
    val cards: Cards,
) {
    fun isHitCard(): Boolean = cards.sum() < BlackJackMachine.BLACKJACK

    fun hitCard(newCard: Card): Player = Player(name = name, cards = cards.add(newCard))

    companion object {
        fun fromName(name: String): Player = Player(name, cards = Cards(cards = List(size = 2) { Card.random() }))
    }
}