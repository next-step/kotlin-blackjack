package blackjack

data class Deck(val cards: ArrayDeque<Card>, var popIndex: Int = 0) {
    constructor(cards: List<Card>) : this(ArrayDeque(cards))

    fun size(): Int {
        return cards.size
    }

    fun pop(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalArgumentException("카드가 없습니다.")
    }

    fun popCards(popCount: Int): Deck {
        return Deck(
            ArrayDeque(cards.take(popCount)).also {
                repeat(popCount) {
                    cards.removeFirstOrNull()
                }
            },
        )
    }
}
