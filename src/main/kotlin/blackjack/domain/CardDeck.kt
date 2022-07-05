package blackjack.domain

class CardDeck {
    private val cards = Symbol.values().flatMap { symbol ->
        CardNumber.values().map { cardNumber ->
            Card(symbol, cardNumber)
        }
    }.asSequence()
        .shuffled()
        .toMutableList()

    val isLeft: Boolean
        get() = cards.size > 0

    fun getOne(): Card = cards.removeFirst()
}
