package blackjack.domain

class Card private constructor(
    val symbol: Symbol,
    val denomination: Denomination
) {

    override fun toString(): String {
        return "$symbol ${denomination.value}"
    }

    companion object {
        val ALL = createAllCards()

        private fun createAllCards(): List<Card> {
            return Symbol.values().flatMap { symbol ->
                Denomination.values().map { denomination -> Card(symbol, denomination) }
            }
        }

        fun of(symbol: Symbol, denomination: Denomination): Card {
            return ALL.asSequence()
                .filter { it.symbol == symbol }
                .find { it.denomination == denomination }
                ?: throw IllegalArgumentException("해당 카드를 찾을 수 없습니다.")
        }

        fun denominationOf(value: String): Card {
            return of(Symbol.SPADE, Denomination.findBy(value))
        }
    }
}
