package blackjack.domain.card

data class Card(private val numberShape: NumberShape, val pattern: Pattern) {

    fun isAce(): Boolean {
        return numberShape == NumberShape.ACE
    }

    fun getValue(): Int {
        return numberShape.value
    }

    override fun toString(): String {
        return "$numberShape$pattern"
    }
}
