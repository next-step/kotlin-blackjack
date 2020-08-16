package blackjack.model.player

import blackjack.model.card.Cards

class Dealer(
    cards: Cards = Cards()
) : Player(
    name = NAME,
    cards = cards
) {
    override fun canMoreCard() =
        cards.canMoreCard() and cards.isSmallThanDealerScore()

    companion object {
        const val NAME = "Dealer"
    }
}
