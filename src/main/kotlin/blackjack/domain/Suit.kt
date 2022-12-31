package blackjack.domain

enum class Suit(
    val symbol: String
) {

    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    companion object {
        fun random(): Suit {
            return values().random()
        }
    }
}
