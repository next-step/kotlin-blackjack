package blackjack.domain.state

import blackjack.domain.Card

interface State {
    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun calculateTotal(): Int

    fun isBlackjack(): Boolean

    fun isBust(): Boolean

    fun getAllCards(): List<Card>
}
