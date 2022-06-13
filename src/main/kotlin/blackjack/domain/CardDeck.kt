package blackjack.domain

object CardDeck {
    private val decks = CardPattern.values().flatMap { pattern ->
        CardValue.values().map { number ->
            Card(pattern, number)
        }
    }.shuffled().toMutableList()
}
