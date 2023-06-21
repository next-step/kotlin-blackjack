package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

class StartState private constructor(private val cards: BlackjackCards) : State {

    override fun draw(card: BlackjackCard): State = Hit(BlackjackCards(cards.plus(card)))

    override fun stop(): State = StartState(cards)

    override fun getCards(): BlackjackCards {
        TODO("Not yet implemented")
    }

    override fun isDrawable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isProceeding(): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        fun start(card1: BlackjackCard, card2: BlackjackCard): State =
            if (isBlackjack(card1, card2)) {
                Blackjack(BlackjackCards(listOf(card1, card2)))
            } else {
                StartState(BlackjackCards(listOf(card1, card2)))
            }

        private fun isBlackjack(card1: BlackjackCard, card2: BlackjackCard): Boolean =
            (card1.number.isAce() && card2.number.isTenScore()) ||
                (card2.number.isAce() && card1.number.isTenScore())
    }
}
