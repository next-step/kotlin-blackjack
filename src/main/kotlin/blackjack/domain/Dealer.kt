package blackjack.domain

class Dealer(
    private val _deck: Cards = Cards(DEFAULT_CARD_DECK),
) {
    val deck: Cards
        get() = _deck

    fun shuffle() = _deck.shuffle()

    fun deliverCard(): Card {
        TODO()
    }
}
