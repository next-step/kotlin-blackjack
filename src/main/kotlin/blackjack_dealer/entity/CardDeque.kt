package blackjack_dealer.entity

class CardDeque(
    private val cardDeque: List<Card>
) : List<Card> by cardDeque {
    companion object {
        fun create(): CardDeque {
            val cardNumber = CardNumber.values()
            val cardShapes = CardShape.values()
            val cardDeque = cardNumber.flatMap { number ->
                cardShapes.map { shape ->
                    Card(
                        cardNumber = number,
                        cardShape = shape
                    )
                }
            }
            return CardDeque(cardDeque)
        }
    }
}
