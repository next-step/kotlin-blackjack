package domain.state

import domain.card.BlackjackCard
import domain.card.BlackjackCards

class Hit(cards: BlackjackCards) : ProceedingState(cards) {

    override fun draw(card: BlackjackCard): State {
        val currentCards = BlackjackCards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards) else Burst(currentCards)
    }

    override fun stop(): State = Stand(BlackjackCards(getCards()))
}
