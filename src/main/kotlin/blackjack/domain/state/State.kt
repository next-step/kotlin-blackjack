package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun hand(): Hand
}
