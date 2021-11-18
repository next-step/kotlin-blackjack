package blackjack.model

data class Dealer(val cards: Cards) {

    fun receive(card: Card): Dealer = if (hasCard(card)) {
        copy(cards = cards)
    } else {
        copy(cards = cards + card)
    }

    private fun hasCard(card: Card): Boolean = card in cards
}
