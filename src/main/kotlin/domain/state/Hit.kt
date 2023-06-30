package domain.state

import domain.card.Card
import domain.card.Cards

class Hit(cards: Cards) : ProceedingState(cards) {

    override fun draw(card: Card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards)
        else Burst(cards = currentCards)
    }

    override fun stop(): State = Stand(cards = getCards())
}
