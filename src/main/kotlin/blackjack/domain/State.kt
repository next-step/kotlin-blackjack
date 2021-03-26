package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.BustState
import blackjack.domain.state.HittableState

internal abstract class State(private val playerCards: PlayerCards) {
    abstract val hittable: Boolean
    abstract val earningsRate: Double

    val cards: List<Card>
        get() {
            return this.playerCards.cards
        }

    fun addCard(card: Card): State {
        if (!this.hittable) {
            throw IllegalStateException("this state can't add card")
        }

        val newPlayerCards = this.playerCards.add(card)

        if (newPlayerCards.isBurst()) {
            return BustState(newPlayerCards)
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
