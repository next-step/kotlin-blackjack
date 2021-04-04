package blackjack.domain

class Card(
    private val shape: CardShape,
    private val type: CardType
) {
    val point = type.point
    val isAce: Boolean
        get() = type.isAce

    override fun toString(): String {
        return "$type$shape"
    }
}
