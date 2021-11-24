package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

sealed class HandResult : Hand {

    final override fun canHit() = false

    final override fun getResult() = this

    final override fun hit(card: Card): Hand {
        throw UnsupportedOperationException()
    }

    abstract fun isBlackJack(): Boolean

    abstract fun isBust(): Boolean

    abstract fun getProfit(bet: Money, other: HandResult): Profit
}

class BlackJack(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = true

    override fun isBust() = false

    override fun getProfit(bet: Money, other: HandResult): Profit {
        if (other.isBlackJack()) {
            return Profit.ZERO
        }
        return Profit(0.5 * bet.value)
    }

}

class Bust(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = false

    override fun isBust() = true

    override fun getProfit(bet: Money, other: HandResult): Profit {
        return Profit(bet.value).negative()
    }

}

class Stay(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = false

    override fun isBust() = false

    /**
     *  주의 : a.getProfit(money, b) != b.getProfit(money, a)
     */
    override fun getProfit(bet: Money, other: HandResult): Profit {
        if (other.isBust()) {
            return Profit(bet.value)
        }
        if (other.isBlackJack()) {
            return Profit(bet.value).negative()
        }
        if (score > other.score) {
            return Profit(bet.value)
        }
        if (score == other.score) {
            return Profit.ZERO
        }
        return Profit(bet.value).negative()
    }
}
