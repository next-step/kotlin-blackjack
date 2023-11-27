package blackjack.domain.model

class Card private constructor(
    val number: CardNumber,
    val shape: CardShape,
) {

    override fun toString(): String {
        return "${number.displayName}${shape.shape}"
    }

    companion object {
        fun of(number: CardNumber, shape: CardShape): Card {
            return Card(number, shape)
        }
    }
}
