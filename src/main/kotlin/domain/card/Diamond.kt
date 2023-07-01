package domain.card

import java.util.concurrent.ConcurrentHashMap

@JvmInline
value class Diamond private constructor(override val denomination: Denomination) : Card {
    companion object {
        private val cards = ConcurrentHashMap<Denomination, Diamond>()

        fun get(denomination: Denomination): Diamond {
            return cards.getOrPut(denomination) {
                Diamond(denomination)
            }
        }

        fun createDeck(): List<Diamond> {
            return Denomination.values()
                .map { Diamond(it) }
        }
    }
}
