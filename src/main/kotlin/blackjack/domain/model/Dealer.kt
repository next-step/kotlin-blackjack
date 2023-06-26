package blackjack.domain.model

import blackjack.domain.Rule

class Dealer(game: Game, cards: Cards = Cards(game = game)) : Player(game, PlayerInfo(NAME), cards) {
    private var hasThirdCard = false

    override fun canGetCard(): Boolean {
        if (hasThirdCard) return false
        hasThirdCard = true
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
    }
}
