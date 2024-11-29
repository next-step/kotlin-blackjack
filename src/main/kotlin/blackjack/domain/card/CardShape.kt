package blackjack.domain.card

enum class CardShape(val symbol: String) {
    Spade("스페이드"),
    Diamond("다이아"),
    Heart("하트"),
    Clover("클로버"),
    ;

    companion object {
        val SHAPE_CACHE = listOf(Spade, Diamond, Heart, Clover)
    }
}
