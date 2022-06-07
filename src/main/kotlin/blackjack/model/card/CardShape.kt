package blackjack.model.card

enum class CardShape(val displayName: String) {
    SPADES("스페이드"),
    DIAMONDS("다이이몬드"),
    HEARTS("하트"),
    CLUBS("클로버");

    companion object {
        val count: Int = CardShape.values().size
    }
}
