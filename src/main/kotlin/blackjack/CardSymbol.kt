package blackjack

@JvmInline
value class CardSymbol(
    private val symbol: String,
) {

    companion object {
        private val SYMBOLS = listOf("하트", "클로버", "다이아", "스페이드")

        fun generateAllKinds() = SYMBOLS.map { CardSymbol(it) }

        fun from(symbols: List<String>) = symbols.map { CardSymbol(it) }
    }
}
