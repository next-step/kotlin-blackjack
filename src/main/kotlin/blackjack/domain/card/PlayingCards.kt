package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.game.BlackjackGame
import blackjack.domain.model.BlackjackErrorCode

class PlayingCards(private val cards: MutableSet<Card> = mutableSetOf()) : Set<Card> by cards {

    fun add(card: Card) {
        check(value = card !in cards) {
            BlackjackErrorCode.CAN_NOT_ADD_DUPLICATE_CARD.message(
                arrayOf(card)
            )
        }

        cards.add(element = card)
    }

    fun includeDenomination(denomination: Denomination): Boolean = cards.any { it.denomination == denomination }

    fun sumScore(): Int = cards.sumOf { it.denomination.score }

    fun isBust(): Boolean = sumScore() > BlackjackGame.BUST_SCORE

    fun isBlackjack(): Boolean = sumScore() == BlackjackGame.BUST_SCORE && cards.size == BlackjackGame.INIT_HAND_COUNT
}
