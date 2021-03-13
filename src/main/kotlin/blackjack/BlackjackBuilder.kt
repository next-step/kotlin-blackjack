package blackjack

class BlackjackBuilder {
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

    fun build(): Blackjack = Blackjack(deck)
}

fun blackjack(initializer: BlackjackBuilder.() -> Unit): BlackjackBuilder {
    return BlackjackBuilder().apply(initializer)
}
