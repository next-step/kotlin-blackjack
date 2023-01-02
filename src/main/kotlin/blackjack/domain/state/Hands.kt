package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point

abstract class Hands(private val _cards: MutableSet<Card>) {
    val cards: Set<Card>
        get() = _cards.toSet()

    fun calculatePoint(): Point {
        val sumPoint = sumPoint()
        return if (sumPoint > Point.MAX) Point.ZERO else sumPoint
    }

    abstract fun sumPoint(): Point

    abstract fun draw(cards: Set<Card>): Hands
    abstract fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int
    abstract fun init(): Hands
}

