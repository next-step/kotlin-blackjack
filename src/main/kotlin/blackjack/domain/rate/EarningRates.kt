package blackjack.domain.rate

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object EarningRates {
    fun getEarningRate(dealer: Dealer, player: Player): EarningRate {
        val earningRates = listOf(BlackJackRate, PlayerWinRate, PlayerLostRate)
        val maybeEarningRate = earningRates.firstOrNull() { it.isAssignable(dealer, player) }
        require(maybeEarningRate != null) { "맞는결과를 찾지 못하였습니다." }
        return maybeEarningRate
    }
}
