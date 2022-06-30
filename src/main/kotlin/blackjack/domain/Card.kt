package blackjack.domain

data class Card(
    val number: CardNumber,
    val suit: CardSuit
) {

    fun isAce(): Boolean {
        return number == CardNumber.ACE
    }
}

class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card> = _cards

    fun add(card: Card) {
        _cards.add(card)
    }
}
