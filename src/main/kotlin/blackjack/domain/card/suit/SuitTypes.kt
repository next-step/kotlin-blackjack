package blackjack.domain.card.suit

enum class SuitTypes(val suit: String) {
    Clover("♣️"),
    Spade("♠️"),
    Heart("♥️️️"),
    Diamond("♦️️");

    override fun toString(): String = suit
}
