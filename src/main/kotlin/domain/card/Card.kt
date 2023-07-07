package domain.card

class Card private constructor(val denomination: Denomination, val cardType: CardType) {

    val numbers: Set<Int>
        get() = denomination.numbers

    override fun toString(): String {
        return "${denomination.text}${cardType.text}"
    }

    companion object {
        private val availableCards: Map<Pair<Denomination, CardType>, Card> =
            combinationOfDenominationAndCardType().associateWith {
                Card(it.first, it.second)
            }

        private fun combinationOfDenominationAndCardType(): List<Pair<Denomination, CardType>> {
            return Denomination.values().flatMap { denomination ->
                CardType.values().map { cardType ->
                    denomination to cardType
                }
            }
        }

        fun of(denomination: Denomination, cardType: CardType): Card {
            return availableCards[denomination to cardType] ?: throw IllegalArgumentException()
        }

        fun all(): List<Card> {
            return availableCards.values.toList()
        }
    }
}
