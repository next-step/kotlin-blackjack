package blackjack.domain

object CardDeck {
    private val cards = Symbol.values().flatMap { symbol ->
        CardNumber.values().map { cardNumber ->
            Card(symbol, cardNumber)
        }
    }.asSequence()
        .shuffled()
        .toMutableList()

    fun isLeft(): Boolean = cards.size > 0

    fun getOne(): Card = cards.removeFirst()
}
