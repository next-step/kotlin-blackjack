package blackjack.domain.game

import blackjack.domain.player.Dealer

interface GameResult {

    fun getProfit(bet: Money, dealer: Dealer): Profit
}
