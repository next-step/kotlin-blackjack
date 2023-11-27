package blackjack.domain.model

class Cards(val cards: List<Card>) {
    companion object {
        fun create(): Cards {
            val numbers = CardNumber.values()
            val shapes = CardShape.values()

            return shapes
                .map { shape -> numbers.map { number -> Card.of(number, shape) } }
                .flatten()
                .run { Cards(this) }
        }
    }
}
