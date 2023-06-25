package blackjack

class Dealer(cards: Cards = Cards()) : Player(PlayerInfo(NAME), cards) {
    override fun canGetCard(): Boolean {
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    companion object {
        const val NAME = "딜러"
    }
}
