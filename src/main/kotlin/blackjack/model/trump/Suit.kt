package blackjack.model.trump

enum class Suit(private val koreanName: String) {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLUB("클로버");

    override fun toString(): String {
        return koreanName
    }
}
