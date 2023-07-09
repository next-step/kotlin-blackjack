package blackjack.domain.card

data class Card(private val numberShape: NumberShape, val pattern: Pattern) {

    fun getValue(): Int {
        if (numberShape == NumberShape.ACE) return 1
        if (numberShape in listOf(NumberShape.KING, NumberShape.QUEEN, NumberShape.JACK)) return 10
        return numberShape.value
    }

    infix operator fun plus(other: Card): Int = this.getValue() + other.getValue()
}
