package blackjack.domain.state

import blackjack.domain.card.Card

interface State {
    fun draw(card: Card): State
    fun stay(): State
    fun score(): Int
    fun isFinished(): Boolean
    fun cards(): List<Card>
    fun earningRate(): Double
}
