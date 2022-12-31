package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Hands
import blackjack.domain.value.BettingAmount

interface State {
    val hands: Hands
    fun draw(cards: Set<Card>): State
    fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int
    fun init(): State
}
