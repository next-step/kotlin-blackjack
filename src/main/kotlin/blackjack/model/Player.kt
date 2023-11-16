package blackjack.model

import blackjack.model.pack.ShuffledPack

data class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) {
    fun deal(card1: Card, card2: Card) {
        cards.add(card1)
        cards.add(card2)
    }

    fun hit() {
        cards.add(ShuffledPack.pickCard())
    }
}
