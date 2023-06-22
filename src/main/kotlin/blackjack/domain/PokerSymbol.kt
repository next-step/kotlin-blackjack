package blackjack.domain

enum class PokerSymbol(val value: Int, val symbolName: String) {
    HEARTS(0, "하트"),
    DIAMONDS(1, "다이아몬드"),
    CLUBS(2, "클로버"),
    SPADES(3, "스페이드");

    companion object {
        fun getSymbolByDrawNumber(drawNumber: Int): PokerSymbol {
            return when (drawNumber / 13) {
                HEARTS.value -> HEARTS
                DIAMONDS.value -> DIAMONDS
                CLUBS.value -> CLUBS
                SPADES.value -> SPADES
                else -> {
                    throw IllegalArgumentException("${drawNumber}는 0과 52사이 값이어야 합니다.")
                }
            }
        }
    }
}
