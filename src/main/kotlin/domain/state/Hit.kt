package domain.state

import domain.card.card
import domain.card.Cards

class Hit(cards: Cards) : ProceedingState(cards) {

    override fun draw(card: card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards) else Burst(currentCards)
    }

    override fun stop(): State = Stand(Cards(getCards()))
}
