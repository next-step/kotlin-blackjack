package blackjack.domain.game

import blackjack.domain.player.Dealer

interface GameResult {

    fun isBlackJack(): Boolean

    fun isBust(): Boolean

    fun getProfit(bet: Money, dealer: Dealer): Profit
}
