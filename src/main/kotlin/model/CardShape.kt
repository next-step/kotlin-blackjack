package model

enum class CardShape(private val shape: String) {
    DIAMONDS("다이아몬드"),
    CLUBS("클로버"),
    HEARTS("하트"),
    SPADES("스페이드");

    companion object {
        fun convertToString(value: CardShape): String {
            return value.shape
        }
    }
}
