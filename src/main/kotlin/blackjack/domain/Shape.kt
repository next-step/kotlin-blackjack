package blackjack.domain

enum class Shape(private val values: String) {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    HEART("하트");

    override fun toString(): String {
        return this.values
    }
}
