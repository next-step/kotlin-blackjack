package blackjack.domain

class Dealer : Participant() {
    override val name: String = "딜러"
    override val cards: Cards = Cards()

    fun shouldReceiveCard(): Boolean {
        return cards.calculateScore() <= Game.DEALER_RECEIVE_CARD_SCORE
    }
}
