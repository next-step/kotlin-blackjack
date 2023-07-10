package blackjack.domain.card

enum class Pattern(private val shape: String) {
    CLUB("클로버"), DIAMOND("다이아몬드"), HEART("하트"), SPADE("스페이드"), ;

    override fun toString(): String {
        return shape
    }
}
