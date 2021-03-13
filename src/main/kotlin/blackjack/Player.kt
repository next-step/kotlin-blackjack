package blackjack

import kotlin.math.abs

interface Player {
    val name: String
    val cards: List<Card>
    fun score(): Int
    fun accept(card: Card)
    fun draw(draw: Draw)

    class Person(override val name: String) : Player {
        private var _cards: List<Card> = emptyList()
        override val cards: List<Card>
            get() = _cards

        override fun score(): Int {
            return _cards.fold(listOf(0)) { accumulator, card ->
                accumulator.flatMap { score -> card.number.map { it + score } }
            }.closeTo(21)
        }

        override fun accept(card: Card) {
            _cards = _cards + card
        }

        override fun draw(draw: Draw) {
            while (draw.accepted(name)) {
                accept(draw.nextCard())
                draw.result(this)
            }
        }

        private fun List<Int>.closeTo(number: Int): Int {
            val sorted = map { it to abs(it - number) }
                .sortedBy { it.second }
                .map { it.first }
            val result = sorted.firstOrNull { it <= 21 }
            return result ?: sorted.firstOrNull() ?: 0
        }
    }
}
