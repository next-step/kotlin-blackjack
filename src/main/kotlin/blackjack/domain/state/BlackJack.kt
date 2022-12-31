package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Hands
import blackjack.domain.value.BettingAmount

class BlackJack(override val hands: Hands) : State {
    override fun draw(cards: Set<Card>): State {
        throw IllegalStateException("더이상 카드를 받을 수 없습니다.")
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int {
        return if (dealer.blackJack()) 0 else bettingAmount.blackJack()
    }

    override fun init(): State = this
}
