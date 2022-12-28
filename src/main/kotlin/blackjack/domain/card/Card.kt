package blackjack.domain.card

class Card(val suit: Suit, val number: Number) {

    fun isAce(): Boolean = number == Number.ACE
}
