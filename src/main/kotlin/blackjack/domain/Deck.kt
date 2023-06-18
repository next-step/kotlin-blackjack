package blackjack.domain

@JvmInline
value class Deck(private val values: MutableSet<Card> = mutableSetOf()) {

    fun add(card: Card) = values.add(card)

    fun remove(card: Card) = values.remove(card)

    fun toList(): List<Card> = values.toList()

    fun score(): Int {
        var result = 0
        for (card in values.sorted()) {
            result += card.score(acc = result)
        }

        return result
    }
}
