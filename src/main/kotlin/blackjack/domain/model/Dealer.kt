package blackjack.domain.model

import blackjack.domain.Rule

class Dealer(round: Round, cards: Cards = Cards(round = round)) : Player(round, PlayerInfo(NAME), cards) {
    override fun canGetCard(): Boolean {
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
    }
}
