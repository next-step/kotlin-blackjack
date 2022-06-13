package blackjack.domain

object CardDeck {
    private val decks = CardPattern.values().flatMap { pattern ->
        CardValue.values().map { number ->
            Card(pattern, number)
        }
    }.shuffled().toMutableList()

    fun all() = decks
    fun start(): Cards {
        return Cards().start(listOf(hit(), hit()))
    }

    fun hit(): Card {
        return decks.removeFirst()
    }
}
