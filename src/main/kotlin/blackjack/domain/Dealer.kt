package blackjack.domain

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards(),
) : Participant(name, cards) {

    override fun canDrawCard(): Boolean {
        return cards.calculateScore() <= THRESHOLD_OF_RECEIVE_CARD
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    companion object {
        private const val THRESHOLD_OF_RECEIVE_CARD = 16
    }
}
