package blackjack.playingcard

data class Cards private constructor(private val elements: MutableList<Card> = mutableListOf()) {
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

    companion object {
        fun from(list: List<Card>): Cards = Cards(list.toMutableList())

        fun empty(): Cards = Cards()
    }
}
