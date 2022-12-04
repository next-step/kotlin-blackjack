package blackjack.domain

class Card(private val cardNumber: CardNumber, private val suit: Suit) {

    private fun toInt(): Int {
        return cardNumber.number
    }

    override fun toString(): String {
        return "${toInt()}${suit.value}"
    }
}

enum class Suit(val value: String) {

    Spade("스페이드"),
    Diamond("다이아몬드"),
    Heart("하트"),
    Clover("클로버")
    ;
}

enum class CardNumber(val number: Int) {

    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    TEN(10),
    King(10),
    Queen(10),
    Jack(10)
    ;
}
