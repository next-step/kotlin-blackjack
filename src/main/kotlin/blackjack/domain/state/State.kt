package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

interface State {
    fun draw(card: Card): State

    fun isFinished(): Boolean

    fun calculateTotal(): Int

    fun isBlackjack(): Boolean

    fun isBust(): Boolean

    fun getAllCards(): List<Card>

    fun hand(): Hand
}
