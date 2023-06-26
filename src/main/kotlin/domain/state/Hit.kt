package domain.state

import domain.card.Card
import domain.card.Cards

class Hit(cards: Cards, betAmount: Int = 0) : ProceedingState(cards, betAmount) {

    override fun draw(card: Card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards, betAmount = getBetAmount())
        else Burst(cards = currentCards, betAmount = getBetAmount())
    }

    override fun stop(): State = Stand(cards = getCards(), betAmount = getBetAmount())
}
