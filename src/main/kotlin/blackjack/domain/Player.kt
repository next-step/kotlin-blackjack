package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardPack
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted

class Player(
    val name: String,
    var state: State = notStarted,
    private val price: Int = 0
) : Participant {

    val isRunning: Boolean
        get() = state.isRunning
    val cardNames: List<String>
        get() = state.cardNames
    val cardSize: Int
        get() = state.cardSize

    override fun takeCard(card: Card) {
        state = state.takeCard(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        val cards = Cards(listOf(card1, card2))
        state = notStarted.takeFirstTwoCards(cards)
    }

    fun stay() {
        state = state.stay()
    }

    override fun cardPointSum(): Int {
        return state.cardPointSum()
    }

    fun profit(dealerState: State): Profit {
        val profitAmount = state.profit(price, dealerState)
        return Profit(this.name, profitAmount)
    }

    fun takeCardIfAccept(hasAccepted: Boolean, card: Card) {
        if (hasAccepted) {
            takeCard(card)
        } else if (state.isRunning) {
            stay()
        }
    }

    companion object {
        private val notStarted = NotStarted()
    }
}
