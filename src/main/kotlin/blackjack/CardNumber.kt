package blackjack

sealed class CardNumber {
    abstract val value: Int
    abstract val name: String

    data class Default(override val value: Int = (2..10).random()) : CardNumber() {
        override val name: String
            get() = value.toString()
    }

    data class Ace(override val value: Int = ACE_VALUE_FIRST) : CardNumber() {
        override val name: String
            get() = "A"

        companion object {
            fun diffValue(): Int = ACE_VALUE_LAST - ACE_VALUE_FIRST
        }
    }

    data class Jack(override val value: Int = JACK_VALUE) : CardNumber() {
        override val name: String
            get() = "J"
    }

    data class Queen(override val value: Int = QUEEN_VALUE) : CardNumber() {
        override val name: String
            get() = "Q"
    }

    data class King(override val value: Int = KING_VALUE) : CardNumber() {
        override val name: String
            get() = "K"
    }

    companion object {
        private const val ACE_NUMBER = 1
        private const val JACK_NUMBER = 11
        private const val QUEEN_NUMBER = 12
        private const val KING_NUMBER = 13

        private const val ACE_VALUE_FIRST = 1
        private const val ACE_VALUE_LAST = 11
        private const val JACK_VALUE = 10
        private const val QUEEN_VALUE = 10
        private const val KING_VALUE = 10

        fun create(value: Int): CardNumber {
            return when (value) {
                ACE_NUMBER -> Ace()
                JACK_NUMBER -> Jack()
                QUEEN_NUMBER -> Queen()
                KING_NUMBER -> King()
                else -> Default(value)
            }
        }
    }
}
