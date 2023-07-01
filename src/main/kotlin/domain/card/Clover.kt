package domain.card

import java.util.concurrent.ConcurrentHashMap

@JvmInline
value class Clover private constructor(override val denomination: Denomination) : Card {
    companion object {
        private val cards = ConcurrentHashMap<Denomination, Clover>()

        fun get(denomination: Denomination): Clover {
            return cards.getOrPut(denomination) {
                Clover(denomination)
            }
        }

        fun createDeck(): List<Clover> {
            return Denomination.values()
                .map { Clover(it) }
        }
    }
}
