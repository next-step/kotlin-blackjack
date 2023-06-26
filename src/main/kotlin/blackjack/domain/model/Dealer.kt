package blackjack.domain.model

import blackjack.domain.Rule

class Dealer(
    trump: Trump,
    cards: Cards = Cards(trump = trump)
) : Player(trump, PlayerInfo(NAME), cards) {
    private var hasThirdCard = false

    override fun canDrawCard(): Boolean {
        if (hasThirdCard) return false
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    override fun drawCard(trump: Trump) {
        hasThirdCard = true
        super.drawCard(trump)
    }

    companion object {
        const val NAME = "딜러"
    }
}
