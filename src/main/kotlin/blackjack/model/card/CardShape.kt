package blackjack.model.card

enum class CardShape(val displayName: String, val score: Int) {
    SPADES("스페이드", 1),
    DIAMONDS("다이이몬드", 2),
    HEARTS("하트", 3),
    CLUBS("클로버", 4);

    companion object {
        val count: Int = CardShape.values().size
    }
}
