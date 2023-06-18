package blackjack.domain.deck

import blackjack.domain.card.Card

@JvmInline
value class Deck(
    private val cards: ArrayDeque<Card> = DeckGenerator.generator(),
) {

    fun multiDraw(count: Int): List<Card> = List(count) { draw() }

    fun draw() = cards.removeFirstOrNull() ?: throw IllegalStateException(EMPTY_DECK_MESSAGE)

    companion object {
        private const val EMPTY_DECK_MESSAGE: String = "덱이 비어있습니다."
    }
}
