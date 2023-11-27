package blackjack.domain.model

class Card(
    val number: CardNumber,
    val shape: CardShape,
) {

    override fun toString(): String {
        return "${number.displayName}${shape.shape}"
    }
}
