package blackjack.domain.card

data class Card(private val numberShape: NumberShape, val pattern: Pattern) {

    fun isAce(): Boolean {
        return numberShape == NumberShape.ACE
    }

    fun getValue(): Int {
        if (numberShape == NumberShape.ACE) return 1
        if (numberShape in listOf(NumberShape.KING, NumberShape.QUEEN, NumberShape.JACK)) return 10
        return numberShape.value
    }

    override fun toString(): String {
        return "${numberShape.display}${pattern.display}"
    }
}
