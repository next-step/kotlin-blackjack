package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Hands
import blackjack.domain.value.BettingAmount

class Hit(override val hands: Hands) : State {
    override fun draw(cards: Set<Card>): State {
        hands.addAll(cards)
        return transfer()
    }

    private fun transfer() = when {
        hands.blackJack() -> BlackJack(hands)
        hands.bust() -> Bust(hands)
        else -> this
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int {
        return when {
            dealer.blackJack() -> bettingAmount.lose()
            dealer.cardPoint() > hands.calculatePoint() -> bettingAmount.lose()
            dealer.cardPoint() == hands.calculatePoint() -> bettingAmount.tie()
            dealer.cardPoint() < hands.calculatePoint() -> bettingAmount.win()
            else -> throw IllegalStateException("수익 금액을 계산할 수 없습니다.")
        }
    }

    override fun init(): State {
        if (hands.cards.isEmpty()) {
            return this
        }
        return transfer()
    }
}
