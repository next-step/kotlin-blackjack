package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Hands
import blackjack.domain.value.BettingAmount

class Bust(override val hands: Hands) : State {
    override fun draw(cards: Set<Card>): State {
        throw IllegalStateException("더이상 카드를 받을 수 없습니다.")
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int = bettingAmount.lose()
    override fun init(): State = this
}
