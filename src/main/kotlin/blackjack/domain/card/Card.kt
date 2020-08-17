package blackjack.domain.card

import blackjack.domain.card.component.Denomination
import blackjack.domain.card.component.Symbol

class Card private constructor(
    private val symbol: Symbol,
    private val denomination: Denomination
) {
    fun isAce() = denomination.isAce()

    fun getScore() = denomination.score

    private fun has(symbol: Symbol): Boolean = this.symbol == symbol

    private fun has(denomination: Denomination): Boolean = this.denomination == denomination

    override fun toString(): String {
        return "$symbol ${denomination.value}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (symbol != other.symbol) return false
        if (denomination != other.denomination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + denomination.hashCode()
        return result
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
                .filter { it.has(symbol) }
                .find { it.has(denomination) }
                ?: throw IllegalArgumentException("Symbol : $symbol, Denomination:  ${denomination}에 해당하는 카드를 찾을 수 없습니다.")
        }

        fun denominationOf(value: String): Card {
            return of(Symbol.SPADE, Denomination.findBy(value))
        }
    }
}
