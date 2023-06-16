package blackjack.domain.card

import blackjack.domain.model.BlackJackErrorCode

class PlayingCards(private val cards: MutableSet<Card> = mutableSetOf()) : Set<Card> by cards {

    fun add(card: Card) {
        check(value = card !in cards) {
            BlackJackErrorCode.CAN_NOT_ADD_DUPLICATE_CARD.message(
                arrayOf(card)
            )
        }

        cards.add(element = card)
    }

    fun isBust(): Boolean = cards.sumOf { it.denomination.getMinimumScore() } > BUST_LIMIT

    companion object {
        private const val ZERO: Int = 0
        private const val BUST_LIMIT: Int = 21
    }
}
