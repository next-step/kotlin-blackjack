package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point

abstract class Hands(private val _cards: MutableSet<Card>) {
    val cards: Set<Card>
        get() = _cards.toSet()

//    fun calculatePoint(): Point = sumPoint()

    abstract fun calculatePoint(): Point

    abstract fun draw(cards: Set<Card>): Hands
    abstract fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int
}
