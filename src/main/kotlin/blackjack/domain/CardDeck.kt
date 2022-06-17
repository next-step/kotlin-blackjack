package blackjack.domain

object CardDeck {
    private val cards = Symbol.values().flatMap { symbol ->
        CardNumber.values().map { cardNumber ->
            Card(symbol, cardNumber)
        }
    }.asSequence()
        .shuffled()
        .toMutableList()

    fun getOne(): Card = cards.removeFirst()
}
