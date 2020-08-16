package blackjack.model.player

import blackjack.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Player(
    name = NAME,
    cards = cards
) {
    override fun gameBatting(cards: Cards) {
        TODO("Not yet implemented")
    }

    companion object {
        const val NAME = "Dealer"
    }
}
