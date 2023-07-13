package blackjack.domain.card

data class Card(private val numberShape: NumberShape, val pattern: Pattern) {

    fun isAce(): Boolean = numberShape == NumberShape.ACE

    fun getValue(): Int = numberShape.value

    override fun toString(): String {
        return "$numberShape$pattern"
    }
}
