package domain.state

import domain.card.card
import domain.card.Cards

class StartState private constructor(private val cards: Cards) : State {

    override fun draw(card: card): State {
        val currentCards = Cards(this.getCards().plus(card))
        return if (currentCards.isDrawable()) Hit(cards = currentCards) else Burst(cards = currentCards)
    }

    override fun stop(): State = Stand(cards)

    override fun getCards(): Cards = this.cards

    companion object {
        fun start(card1: card, card2: card): State =
            if (Cards.isBlackjack(card1, card2)) {
                Blackjack(Cards(listOf(card1, card2)))
            } else {
                StartState(Cards(listOf(card1, card2)))
            }
    }
}
