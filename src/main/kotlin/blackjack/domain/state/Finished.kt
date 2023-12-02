package blackjack.domain.state

import blackjack.domain.card.Card

abstract class Finished : State {
    abstract val rate: Double

    override fun draw(card: Card): State {
        throw IllegalStateException()
    }

    override fun stay(): State = Stay()

    fun profit(money: Int): Double = money * rate
}
