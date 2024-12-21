package blackjack.domain

import java.math.BigDecimal

sealed interface State {
    val hand: Hand

    val isDone: Boolean

    fun drawFrom(deck: Deck): State

    fun stand(): State

    fun profit(bet: Bet): BigDecimal
}
