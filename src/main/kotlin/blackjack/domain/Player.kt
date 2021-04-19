package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted
import blackjack.domain.state.started.run.Hit
import blackjack.domain.state.started.finished.BlackJack

class Player(
    val name: String,
    var state: State = NotStarted(),
    private val price: Int = 0
) : Participant {

    val cardNames: List<String>
        get() = state.cardNames
    val cardSize: Int
        get() = state.cardSize

    override fun takeCard(card: Card) {
        state = state.takeCard(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        val cards = Cards(listOf(card1, card2))
        state = if (cards.isBlackjack) BlackJack(cards) else Hit(cards)
    }

    override fun stay() {
        state = state.stay()
    }

    override fun cardPointSum(): Int {
        return state.cardPointSum()
    }

    fun profit(dealerState: State): Profit {
        val profitAmount = state.profit(price, dealerState)
        return Profit(this.name, profitAmount)
    }
}
