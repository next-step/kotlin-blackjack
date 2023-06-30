package domain.card

import java.util.concurrent.ConcurrentHashMap

@JvmInline
value class Spade private constructor(override val denomination: Denomination) : Card {
    companion object {
        private val cards = ConcurrentHashMap<Denomination, Spade>()

        fun get(denomination: Denomination): Spade {
            return cards.getOrPut(denomination) {
                Spade(denomination)
            }
        }
    }
}
