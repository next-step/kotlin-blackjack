package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import java.math.BigDecimal

interface State {
    fun takeFirstTwoCards(cards: Cards): State
    fun takeCard(card: Card): State
    fun cardPointSum(): Int
}
