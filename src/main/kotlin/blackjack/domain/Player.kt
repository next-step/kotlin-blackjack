package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted

class Player(
    val name: String,
    var state: State = NotStarted(),
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
        listOf(card1, card2).forEach {
            state = state.takeCard(it)
        }
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
}
