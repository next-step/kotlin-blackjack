package blackjack.domain

enum class Suit(
    val value: String
) {
    SPADE("스페이드"),
    HEART("하트"),
    CLUB("클로버");

    companion object {
        fun random(): Suit {
            return values().random()
        }
    }
}