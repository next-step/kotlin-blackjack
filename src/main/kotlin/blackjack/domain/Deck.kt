package blackjack.domain

class Deck(values: MutableSet<Card> = mutableSetOf()) {
    private val internalValues: MutableSet<Card> = values.toMutableSet()

    val size: Int
        get() = internalValues.size

    fun add(card: Card) = internalValues.add(card)

    fun remove(card: Card) = internalValues.remove(card)

    fun toList(): List<Card> = internalValues.toList()

    fun pick(count: Int): Set<Card> {
        require(internalValues.size > count) {
            "더 이상 뽑을 카드가 없습니다. 현재 카드는 ${internalValues.size}개 남아있습니다."
        }
        val result = internalValues.shuffled().take(count).toSet()
        internalValues.removeAll(result)

        return result
    }

    fun score(): Int {
        var result = 0
        for (card in internalValues.sorted()) {
            result += card.score(acc = result)
        }

        return result
    }

    fun copy(): Deck = Deck(internalValues.toMutableSet())
}
