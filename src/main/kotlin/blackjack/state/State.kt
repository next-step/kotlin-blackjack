package blackjack.state

import blackjack.model.Card
import blackjack.model.Cards

interface State {

    val cards: Cards

    fun draw(card: Card): State

    fun profit(amount: Double): Double

    fun stay(): State
}




