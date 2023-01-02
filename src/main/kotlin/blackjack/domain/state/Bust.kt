package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount

class Bust(override val _cards: MutableSet<Card>) : Hands {
    override fun draw(cards: Set<Card>): Hands {
        throw IllegalStateException("더이상 카드를 받을 수 없습니다.")
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int = bettingAmount.lose()
    override fun init(): Hands = this
}
