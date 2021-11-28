package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.player.Dealer

sealed class HandResult : Hand, GameResult {

    final override fun canHit() = false

    final override fun getResult() = this

    final override fun hit(card: Card): Hand {
        throw UnsupportedOperationException()
    }
}

class BlackJack(override val cards: Cards) : HandResult() {

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        if (dealer.isBlackJack()) {
            return Profit.draw()
        }
        return Profit.blackJack(bet)
    }
}

class Bust(override val cards: Cards) : HandResult() {

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        return Profit.lose(bet)
    }
}

class Stay(override val cards: Cards) : HandResult() {

    override fun getProfit(bet: Money, dealer: Dealer): Profit {
        if (dealer.isBust()) {
            return Profit.win(bet)
        }
        if (dealer.isBlackJack()) {
            return Profit.lose(bet)
        }
        if (score > dealer.score) {
            return Profit.win(bet)
        }
        if (score == dealer.score) {
            return Profit.draw()
        }
        return Profit.lose(bet)
    }
}
