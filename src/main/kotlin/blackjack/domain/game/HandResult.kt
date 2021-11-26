package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.Dealer

sealed class HandResult : Hand, GameResult {

    final override fun canHit() = false

    final override fun getResult() = this

    final override fun hit(card: Card): Hand {
        throw UnsupportedOperationException()
    }
}

class BlackJack(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = true

    override fun isBust() = false

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        if (dealer.isBlackJack()) {
            return Profit.ZERO
        }
        return Profit(BlackJackProfit * bet.value)
    }

    companion object {
        private const val BlackJackProfit = 0.5
    }

}

class Bust(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = false

    override fun isBust() = true

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        return Profit(bet.value).negative()
    }

}

class Stay(override val cards: Cards) : HandResult() {

    override fun isBlackJack() = false

    override fun isBust() = false

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        if (dealer.isBust()) {
            return Profit(bet.value)
        }
        if (dealer.isBlackJack()) {
            return Profit(bet.value).negative()
        }
        if (score > dealer.score) {
            return Profit(bet.value)
        }
        if (score == dealer.score) {
            return Profit.ZERO
        }
        return Profit(bet.value).negative()
    }
}
