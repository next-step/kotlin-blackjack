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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Person

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class Dealer(private val player: Player) : Player by player {
        constructor() : this(Person("dealer"))

        fun take(nextCard: () -> Card): Boolean {
            return (score() <= 16).also {
                if (it) {
                    accept(nextCard())
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Dealer

            if (player != other.player) return false

            return true
        }

        override fun hashCode(): Int {
            return player.hashCode()
        }
    }
}

infix fun Player.vs(dealer: Player): PlayResult {
    val myScore = score()
    val dealerScore = dealer.score()
    return when {
        myScore > dealerScore -> PlayResult.WINS
        myScore < dealerScore -> PlayResult.LOSSES
        else -> PlayResult.DRAWS
    }
}
