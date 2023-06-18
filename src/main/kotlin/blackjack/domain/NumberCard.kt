package blackjack.domain

class NumberCard(override val symbol: SymbolType, private val number: Int) : Card {
    override val priority: Int = 1

    override fun name(): String = number.toString()

    override fun score(acc: Int): Int = number

    init {
        require(number in numberRange) {
            "숫자 카드의 값은 $MIN_LIMIT ~ $MAX_LIMIT 사이어야 합니다."
        }
    }

    companion object {
        const val MIN_LIMIT = 2
        const val MAX_LIMIT = 9
        private val numberRange = (MIN_LIMIT..MAX_LIMIT).toList()
    }
}
