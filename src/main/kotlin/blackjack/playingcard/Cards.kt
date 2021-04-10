package blackjack.playingcard

class Cards(elements: List<Card> = emptyList()) {
    private val elements = elements.toMutableList()

    fun toList(): List<Card> {
        return elements
    }

    fun add(card: Card) {
        elements.add(card)
    }

    fun sum(): Value {
        return elements.sortedBy { it.symbol }
            .fold(Value.ZERO) { sum, card -> sum + card.valueBy(sum) }
    }
}
