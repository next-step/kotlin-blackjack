package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount

class Hit(override val _cards: MutableSet<Card>) : Hands {
    override fun draw(cards: Set<Card>): Hands {
        _cards.addAll(cards)
        return transfer()
    }

    private fun transfer() = when {
        blackJack() -> BlackJack(_cards)
        bust() -> Bust(_cards)
        else -> this
    }

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int {
        return when {
            dealer.blackJack() -> bettingAmount.lose()
            dealer.cardPoint() > calculatePoint() -> bettingAmount.lose()
            dealer.cardPoint() == calculatePoint() -> bettingAmount.tie()
            dealer.cardPoint() < calculatePoint() -> bettingAmount.win()
            else -> throw IllegalStateException("수익 금액을 계산할 수 없습니다.")
        }
    }

    override fun init(): Hands {
        if (cards.isEmpty()) {
            return this
        }
        return transfer()
    }
}
