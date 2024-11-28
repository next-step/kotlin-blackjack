package blackjack.player

import blackjack.card.Card

class Player(
    val name: String,
    val cards: List<Card>,
) {
    companion object {
        fun from(name: String): Player = Player(name, cards = List(size = 2) { Card.random() })
    }
}
