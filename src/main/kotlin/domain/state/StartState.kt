package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

class StartState private constructor(private val cards: BlackjackCards) : State {

    override fun draw(card: BlackjackCard): State {
        val currentCards = BlackjackCards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards) else Burst(cards = currentCards)
    }

    override fun stop(): State = Stand(cards)

    override fun getCards(): BlackjackCards = this.cards

    override fun isDrawable(): Boolean = true

    override fun isProceeding(): Boolean = true
    override fun getScoreSum(): Int = cards.sum

    companion object {
        fun start(card1: BlackjackCard, card2: BlackjackCard): State =
            if (Blackjack.isBlackjack(card1, card2)) {
                Blackjack(BlackjackCards(listOf(card1, card2)))
            } else {
                StartState(BlackjackCards(listOf(card1, card2)))
            }
    }
}
