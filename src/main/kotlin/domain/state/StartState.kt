package domain.state

import domain.card.Card
import domain.card.Cards

class StartState private constructor(private val cards: Cards) : State {

    override fun draw(card: Card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards) else Burst(cards = currentCards)
    }

    override fun stop(): State = Stand(cards)

    override fun getCards(): Cards = this.cards

    companion object {
        fun start(cards: Cards): State =
            if (cards.isBlackjack()) {
                Blackjack(cards)
            } else {
                StartState(cards)
            }
    }
}
