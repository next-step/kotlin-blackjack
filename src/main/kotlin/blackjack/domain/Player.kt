package blackjack.domain

class Player(val name: String) {
    private val _cards: Cards = Cards()
    val cards: Cards
        get() = _cards.deepCopy()

    fun takeOneMoreCard(card: Card) = _cards.add(card)
    fun takeFirstTwoCard(cards: Cards) = cards.forEach(_cards::add)
}
