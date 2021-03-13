package blackjack

import kotlin.math.abs

interface Player {
    val name: String
    val cards: List<Card>
    fun score(): Int
    fun draw(card: Card)
    fun draw(
        nextCard: () -> Card,
        isDraw: (name: String) -> Boolean = { true }
    )

    class Person(override val name: String) : Player {
        private var _cards: List<Card> = emptyList()
        override val cards: List<Card>
            get() = _cards

        override fun score(): Int {
            return _cards.fold(listOf(0)) { acc, card ->
                acc.flatMap { score -> card.number.map { it + score } }
            }.closeTo(21)
        }

        override fun draw(card: Card) {
            _cards = _cards + card
        }

        override fun draw(nextCard: () -> Card, isDraw: (name: String) -> Boolean) {
            if (isDraw(name)) {
                draw(nextCard())
            }
        }

        private fun List<Int>.closeTo(number: Int): Int {
            val sorted = map { it to abs(it - number) }
                .sortedBy { it.second }
                .map { it.first }
            val result = sorted.firstOrNull { it <= 21 }
            return result ?: sorted.firstOrNull() ?: 0
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Person

            if (name != other.name) return false
            if (_cards != other._cards) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + _cards.hashCode()
            return result
        }
    }
}
