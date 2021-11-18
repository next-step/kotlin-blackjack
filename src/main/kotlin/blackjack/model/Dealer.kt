package blackjack.model

data class Dealer(val cards: Cards) {

    val canReceive: Boolean = cards.sum() <= RECEIVE_CARD_LIMIT

    fun receive(card: Card): Dealer = if (hasCard(card)) {
        copy(cards = cards)
    } else {
        copy(cards = cards + card)
    }

    private fun hasCard(card: Card): Boolean = card in cards

    companion object {
        private const val RECEIVE_CARD_LIMIT = 16
    }
}
