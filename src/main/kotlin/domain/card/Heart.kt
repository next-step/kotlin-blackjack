package domain.card

import java.util.concurrent.ConcurrentHashMap

@JvmInline
value class Heart private constructor(override val denomination: Denomination) : Card {

    override fun toString(): String {
        return "${denomination}하트"
    }
    companion object {
        private val cards = ConcurrentHashMap<Denomination, Heart>()

        fun get(denomination: Denomination): Heart {
            return cards.getOrPut(denomination) {
                Heart(denomination)
            }
        }

        fun createDeck(): List<Heart> {
            return Denomination.values()
                .map { Heart(it) }
        }
    }
}
