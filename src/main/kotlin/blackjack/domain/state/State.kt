package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

interface State {

    val cards: Cards

    fun isTakeable(): Boolean
    fun draw(card: Card): State
    fun stay(): Stay
}
