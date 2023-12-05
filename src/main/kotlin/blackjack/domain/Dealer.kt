package blackjack.domain

class Dealer(
    name: String = DEFAULT_NAME,
    cards: Cards = Cards(),
) : Participant(name, cards) {
    fun shouldReceiveCard(): Boolean {
        return score <= Game.DEALER_RECEIVE_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
    }
}
