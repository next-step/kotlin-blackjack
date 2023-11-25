package blackjack

class Card private constructor(
    val number: Number,
    val shape: Shape,
) {
    val isAce = number.isAce()
    val value = number.value

    companion object {
        fun diamond(number: Number): Card {
            return Card(number, Shape.DIAMOND)
        }

        fun clover(number: Number): Card {
            return Card(number, Shape.CLOVER)
        }

        fun heart(number: Number): Card {
            return Card(number, Shape.HEART)
        }

        fun spade(number: Number): Card {
            return Card(number, Shape.SPADE)
        }
    }
}
