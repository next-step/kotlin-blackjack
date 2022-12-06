package blackjack

sealed class CardNumber {
    abstract val value: Int
    abstract val name: String

    data class Default(override val value: Int = (2..10).random()) : CardNumber() {
        override val name: String
            get() = value.toString()
    }

    data class Ace(override val value: Int = 1) : CardNumber() {
        override val name: String
            get() = "Ace"
    }

    data class Jack(override val value: Int = 10) : CardNumber() {
        override val name: String
            get() = "Jack"
    }

    data class Queen(override val value: Int = 10) : CardNumber() {
        override val name: String
            get() = "Queen"
    }

    data class King(override val value: Int = 10) : CardNumber() {
        override val name: String
            get() = "King"
    }
}
