package blackjack.domain.model

import blackjack.domain.interfaces.Drawable

@JvmInline
value class Deck private constructor(private val cards: Cards) {

    fun peek(drawable: Drawable) = drawable.draw(cards.peek())
    fun peek(drawables: List<Drawable>) = drawables.forEach { drawable -> peek(drawable) }

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
