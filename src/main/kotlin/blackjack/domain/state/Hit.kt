package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point

class Hit(private val _cards: MutableSet<Card>) : Hands(_cards) {

    override fun calculatePoint(): Point {
        if (hard()) return _cards.sumOf { it.point }
        return _cards.sumOf { it.point }
            .soft()
    }

    private fun hard() = !_cards.any { it.isAce() }

    override fun draw(cards: Set<Card>): Hands {
        _cards.addAll(cards)
        return transfer()
    }

    private fun transfer() = when {
        blackJack() -> BlackJack(_cards)
        bust() -> Bust(_cards)
        else -> this
    }

    private fun blackJack() = cards.size == 2 && calculatePoint() == Point.BLACK_JACK
    private fun bust(): Boolean = calculatePoint() > Point.MAX

    override fun earning(dealer: Dealer, bettingAmount: BettingAmount): Int {
        return when {
            dealer.blackJack() -> bettingAmount.lose()
            dealer.cardPoint() > calculatePoint() -> bettingAmount.lose()
            dealer.cardPoint() == calculatePoint() -> bettingAmount.tie()
            dealer.cardPoint() < calculatePoint() -> bettingAmount.win()
            else -> throw IllegalStateException("수익 금액을 계산할 수 없습니다.")
        }
    }
}

private fun <E> Set<E>.sumOf(function: (E) -> Point): Point {
    return this.map(function)
        .reduce { acc, point -> acc + point }
}
