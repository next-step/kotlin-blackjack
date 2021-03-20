package blackjack.domain

enum class CardSuit(
    private val alias: String
) {
    CLUBS("클로버"), DIAMONDS("다이아몬드"), HEARTS("하트"), SPADES("스페이드");

    override fun toString(): String {
        return alias
    }
}
