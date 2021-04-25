package blackjack.domain.state

import blackjack.domain.card.Card
import java.math.BigDecimal

interface State {
    val isRunning: Boolean
    val cardNames: List<String>
    val isBust: Boolean
    val isBlackJack: Boolean

    val cardSize: Int

    fun takeCard(card: Card): State
    fun cardPointSum(): Int
    fun profit(betAmount: Int, dealerState: State): BigDecimal
    fun stay(): State
}
