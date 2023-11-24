package blackjack.domain.model

@JvmInline
value class Deck private constructor(private val cards: Cards) {

    fun peek(): Card = cards.peek()

    fun size(): Int = cards.cards.size

    companion object {
        fun all(): Deck {
            val cards = Cards.from(Pattern.values()
                .flatMap { pattern ->
                    Sign.values()
                        .map { sign -> Card.of(pattern, sign) }
                }
                .shuffled()
                .toSet())
            return Deck(cards)
        }
    }
}
