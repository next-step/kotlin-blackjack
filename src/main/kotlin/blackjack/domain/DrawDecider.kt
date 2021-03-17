package blackjack.domain

enum class DrawDecider(val symbol: String) {
    DRAW("y"),
    STAND("n");

    companion object {
        fun of(symbol: String): DrawDecider = values().find { it.symbol == symbol.trim() }
            ?: throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
    }
}
