package blackjack.domain.card

class Card(val suit: Suit, number: Number) {
    private val _number = number
    val number = _number.value
    val otherNumber = _number.orValue

    fun isAce(): Boolean = _number == Number.ACE
}
