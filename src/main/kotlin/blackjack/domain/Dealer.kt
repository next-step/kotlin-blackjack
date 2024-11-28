package blackjack.domain

class Dealer(
    val cards: Cards = Cards(),
) {

    fun needReceiveCard(): Boolean {
        return cards.calculateScore() <= THRESHOLD_OF_RECEIVE_CARD
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val THRESHOLD_OF_RECEIVE_CARD = 16
    }
}
