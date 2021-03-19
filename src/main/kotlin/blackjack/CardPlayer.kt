package blackjack

interface CardPlayer {
    val name: String
    val hand: Hand
    fun score(): Int
    fun accept(card: Card)
    fun draw(draw: Draw)
    fun busts(): Boolean
    fun blackjack(): Boolean

    companion object {
        const val BLACKJACK = 21
    }

    class Player(override val name: String, private var _cards: List<Card> = emptyList()) : CardPlayer {

        override val hand: Hand
            get() = Hand(_cards)

        override fun score(): Int = hand.score()

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

        override fun blackjack(): Boolean = hand.size == 2 && score() == BLACKJACK

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
