package blackjack.domain.card

enum class CardShape(
    private val expression: String
) {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이드");

    override fun toString(): String {
        return expression
    }
}
