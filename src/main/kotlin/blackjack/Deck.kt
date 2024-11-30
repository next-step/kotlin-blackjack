package blackjack

data class Deck(val cards: List<Card>) {
    private val shuffleIndex: ArrayDeque<Int> = cards.indices.shuffled().toCollection(ArrayDeque())

    fun size(): Int {
        return shuffleIndex.size
    }

    fun pop(): Card {
        val index = shuffleIndex.firstOrNull() ?: throw IllegalArgumentException("카드가 없습니다.")
        return popOf(index)
    }

    fun popOf(index: Int): Card {
        shuffleIndex.remove(index)
        return cards[index]
    }

    fun popCards(popCount: Int): Deck {
        return Deck(
            ArrayDeque(cards.take(popCount)).also {
                repeat(popCount) {
                    pop()
                }
            },
        )
    }
}
