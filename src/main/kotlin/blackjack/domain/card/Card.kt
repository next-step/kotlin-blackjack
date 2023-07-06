package blackjack.domain.card

data class Card(private val value: String, val pattern: Pattern) {

    fun getValue(): Int {
        if (value == "A") return 1
        if (value in listOf("K", "Q", "J")) return 10
        return value.toInt()
    }

    infix operator fun plus(other: Card): Int = this.getValue() + other.getValue()
}
