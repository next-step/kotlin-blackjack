package blackjack.domain

class DeckBuilder {
    var deck: List<Card> = emptyList()

    fun normal(numbers: IntRange) {
        deck = deck + numbers.map { toSymbolCard(it.toString()) }.flatten()
    }

    fun ace() {
        deck = deck + toSymbolCard("A")
    }

    fun jack() {
        deck = deck + toSymbolCard("J")
    }

    fun queen() {
        deck = deck + toSymbolCard("Q")
    }

    fun king() {
        deck = deck + toSymbolCard("K")
    }

    private fun toSymbolCard(name: String): List<Card> {
        return Symbol.values().map { Card(name, it) }
    }

    fun build(): Deck = Deck(deck)
}

fun deck(initializer: DeckBuilder.() -> Unit): DeckBuilder {
    return DeckBuilder().apply(initializer)
}
