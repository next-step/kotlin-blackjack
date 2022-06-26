package blackjack.domain

data class Card(
    val cardNumber: CardNumber,
    val cardSuit: CardSuit
) {

    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }
}

class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) : MutableList<Card> by _cards {

    val cards: List<Card> = _cards
}
