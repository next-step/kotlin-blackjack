package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.BurstState
import blackjack.domain.state.HittableState

internal abstract class State(private val playerCards: PlayerCards) {
    abstract fun canHit(max: Int): Boolean
    abstract fun earningsRate(): Double

    val cards: List<Card>
        get() {
            return this.playerCards.cards
        }

    fun addCard(card: Card): State {
        val newPlayerCards = this.playerCards.add(card)
        if (newPlayerCards.isBurst()) {
            return BurstState(newPlayerCards)
        }

        if (newPlayerCards.isBlackJack()) {
            return Blackjack(newPlayerCards)
        }

        return HittableState(newPlayerCards)
    }

    fun score(): Int {
        return this.playerCards.score()
    }
}
