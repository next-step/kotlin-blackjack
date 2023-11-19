package blackjack.model

import blackjack.model.pack.Pack

data class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) {
    fun deal(pack: Pack) {
        cards.add(pack.pickCard())
        cards.add(pack.pickCard())
    }

    fun hit(pack: Pack) {
        cards.add(pack.pickCard())
    }
}
