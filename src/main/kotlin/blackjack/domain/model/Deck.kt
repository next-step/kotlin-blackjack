package blackjack.domain.model

import blackjack.domain.model.player.AbstractPlayer

@JvmInline
value class Deck private constructor(private val cards: Cards) {

    fun peek(drawable: AbstractPlayer) = drawable.draw(cards.peek())
    fun peek(drawables: List<AbstractPlayer>) = drawables.forEach { drawable -> peek(drawable) }

    fun size(): Int = cards.values.size

    companion object {
        fun all(): Deck {
            val cards = Cards.from(
                Pattern.values().flatMap { pattern ->
                    Sign.values().map { sign -> Card.of(pattern, sign) }
                }.shuffled().toSet()
            )
            return Deck(cards)
        }
    }
}
