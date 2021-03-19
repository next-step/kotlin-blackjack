package blackjack

import kotlin.math.abs

interface CardPlayer {
    val name: String
    val cards: List<Card>
    fun score(): Int
    fun accept(card: Card)
    fun draw(draw: Draw)
    fun busts(): Boolean

    companion object {
        const val BLACKJACK = 21
    }

    class Player(override val name: String, private var _cards: List<Card> = emptyList()) : CardPlayer {

        override val cards: List<Card>
            get() = _cards

        override fun score(): Int {
            return _cards.fold(listOf(0)) { accumulator, card ->
                accumulator.flatMap { score -> card.number.map { it + score } }
            }.closeTo(BLACKJACK)
        }

        override fun accept(card: Card) {
            _cards = _cards + card
        }

        override fun draw(draw: Draw) {
            while (score() < BLACKJACK && draw.accepted(name)) {
                accept(draw.nextCard())
                draw.result(this)
            }
        }

        override fun busts(): Boolean = score() > BLACKJACK

        private fun List<Int>.closeTo(number: Int): Int {
            val sorted = map { it to abs(it - number) }
                .sortedBy { it.second }
                .map { it.first }
            val result = sorted.firstOrNull { it <= BLACKJACK }
            return result ?: sorted.firstOrNull() ?: 0
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Player

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class Dealer(private val player: CardPlayer) : CardPlayer by player {
        constructor() : this(Player("dealer"))

        fun lastWant() = score() <= HIT_UNTIL

        fun take(last: Card) {
            accept(last)
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

        companion object {
            const val HIT_UNTIL = 16
        }
    }
}
