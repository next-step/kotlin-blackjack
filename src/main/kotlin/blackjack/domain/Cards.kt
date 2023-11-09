package blackjack.domain

@JvmInline
value class Cards(val cards: MutableSet<Card> = mutableSetOf()) {

    fun add(card: Card) {
        cards.add(card)
    }

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun score(): Score {
        val first = Score(cards.sumOf { it.calculateScore().first })
        val second = Score(cards.sumOf { it.calculateScore().second })
        if (first.isBurst().not() && second.isBurst().not()) {
            return Score(maxOf(first.score, second.score))
        }
        return Score(minOf(first.score, second.score))
    }
}
