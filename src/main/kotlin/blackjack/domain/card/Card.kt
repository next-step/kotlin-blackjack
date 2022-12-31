package blackjack.domain.card

data class Card(val shape: Shape, val denomination: Denomination) {
    val score: Int
        get() = denomination.score

    val isAce: Boolean
        get() = denomination == Denomination.ACE

    override fun toString(): String {
        return "${denomination.initial}${shape.values}"
    }
}
