package blackjack.model

import blackjack.model.pack.ShuffledPack

data class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) {
    fun deal() {
        cards.add(ShuffledPack.pickCard())
        cards.add(ShuffledPack.pickCard())
    }

    fun hit() {
        cards.add(ShuffledPack.pickCard())
    }
}
