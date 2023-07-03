package blackjack.domain

import blackjack.domain.Card.Companion.ALL_CARDS
import blackjack.error.BlackjackErrorMessage.CAN_NOT_DRAW_THIS_CARD
import blackjack.error.BlackjackErrorMessage.NO_MORE_REMAINING_CARDS
import kotlin.random.Random

class GameCardsSet {
    private val drawnCards: MutableSet<Card> = mutableSetOf()
    private val remainingCards: List<Card>
        get() = ALL_CARDS.values.filter { it !in drawnCards }

    fun drawRandomCard(): Card {
        check(remainingCards.isNotEmpty()) { NO_MORE_REMAINING_CARDS }

        val randomIndex = Random.nextInt(remainingCards.size)
        val drawnCard = remainingCards[randomIndex]
        drawnCards.add(drawnCard)
        return drawnCard
    }

    fun drawSpecificCard(card: Card): Card {
        check(remainingCards.isNotEmpty()) { NO_MORE_REMAINING_CARDS }
        check(remainingCards.contains(card)) { CAN_NOT_DRAW_THIS_CARD }

        drawnCards.add(card)
        return card
    }
}
