package blackjack.domain

class Card(
    private val shape: CardShape,
    val type: CardType
) {
    val point = type.point

    override fun toString(): String {
        return "${type.expression}${shape.expression}"
    }
}
