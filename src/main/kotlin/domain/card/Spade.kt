package domain.card

import java.util.concurrent.ConcurrentHashMap

@JvmInline
value class Spade private constructor(override val denomination: Denomination) : Card {

    override fun toString(): String {
        return "${denomination}스페이드"
    }
    companion object {
        private val cards = ConcurrentHashMap<Denomination, Spade>()

        fun get(denomination: Denomination): Spade {
            return cards.getOrPut(denomination) {
                Spade(denomination)
            }
        }

        fun createDeck(): List<Spade> {
            return Denomination.values()
                .map { Spade(it) }
        }
    }
}
