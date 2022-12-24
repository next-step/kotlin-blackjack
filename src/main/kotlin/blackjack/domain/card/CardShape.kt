package blackjack.domain.card

enum class CardShape(
    private val description: String
) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아"),
    CLUB("클로버");

    override fun toString(): String {
        return description
    }
}
