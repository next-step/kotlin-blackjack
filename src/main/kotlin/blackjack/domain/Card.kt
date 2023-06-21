package blackjack.domain

data class Card(val suit: Suit, val cardNumber: CardNumber) {
    val points: List<Int>
        get() = cardNumber.points

    override fun toString(): String {
        return cardNumber.toString() + suit.value
    }
}

enum class Suit(val value: String) {
    SPADE("스페이드"), HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버")
}
