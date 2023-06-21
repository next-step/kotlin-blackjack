package blackjack.domain

data class Card(val suit: Suit, val cardNumber: CardNumber)

enum class Suit(val value: String) {
    SPADE("스페이드"), HEART("하트"), DIAMOND("다이아몬드"), CLOVER("클로버")
}
