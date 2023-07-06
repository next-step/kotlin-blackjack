package domain.card

import java.util.concurrent.ConcurrentHashMap

class Card private constructor(val denomination: Denomination, val cardType: CardType) {

    val numbers: Set<Int>
        get() = denomination.numbers

    companion object {
        private val spades = ConcurrentHashMap<Denomination, Card>()
        private val hearts = ConcurrentHashMap<Denomination, Card>()
        private val diamonds = ConcurrentHashMap<Denomination, Card>()
        private val clovers = ConcurrentHashMap<Denomination, Card>()

        fun spade(denomination: Denomination): Card {
            return spades.getOrPut(denomination) {
                Card(denomination, CardType.SPADE)
            }
        }

        fun heart(denomination: Denomination): Card {
            return hearts.getOrPut(denomination) {
                Card(denomination, CardType.HEART)
            }
        }

        fun diamond(denomination: Denomination): Card {
            return diamonds.getOrPut(denomination) {
                Card(denomination, CardType.DIAMOND)
            }
        }

        fun clover(denomination: Denomination): Card {
            return clovers.getOrPut(denomination) {
                Card(denomination, CardType.CLOVER)
            }
        }
    }
}
