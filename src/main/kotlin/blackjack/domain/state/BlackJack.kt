package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point

class BlackJack(_cards: MutableSet<Card>) : Hands(_cards) {

    override fun calculatePoint(): Point = Point.BLACK_JACK

    override fun draw(cards: Set<Card>): Hands {
        throw IllegalStateException("더이상 카드를 받을 수 없습니다.")
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int {
        return if (dealer.blackJack()) 0 else bettingAmount.blackJack()
    }
}
