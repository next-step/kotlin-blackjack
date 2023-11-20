package blackjack_dealer.entity

object CardDeque {
    private var _cardDeque: ArrayDeque<Card> = ArrayDeque()
    val cardDeque get() = _cardDeque

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
        }.shuffled()
        return CardDeque.apply {
            _cardDeque = ArrayDeque(cardDeque)
        }
    }
}
