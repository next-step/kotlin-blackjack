package blackjack

data class Card(
    val number: CardNumber,
    val suit: CardSuit,
) {
    override fun toString(): String {
        return "${number.description}${suit.description}"
    }

    fun isAce(): Boolean {
        return number == CardNumber.ACE
    }
}
