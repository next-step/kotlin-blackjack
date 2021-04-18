package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import java.math.BigDecimal

interface State {
    val cardSize: Int
        get() = 0
    val cardNames: List<String>

    fun takeFirstTwoCards(cards: Cards): State
    fun takeCard(card: Card): State
    fun cardPointSum(): Int
    fun profit(betAmount: Int, dealerState: State): BigDecimal
    fun stay(): State
}
