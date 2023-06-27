package blackjack.domain.model

import blackjack.domain.Rule

class Dealer(
    trump: Trump,
    cards: Cards = Cards(trump = trump)
) : Player(trump, PlayerInfo(NAME), cards) {
    override fun canDrawCard(): Boolean {
        if (cards.items.size >= MAXIMUM_CARD_COUNT) return false
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
        private const val MAXIMUM_CARD_COUNT = 3
    }
}
