package blackjack.domain.calculator

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.status.PlayerStatus
import java.math.BigDecimal

object BettingCalculator {
    private val RATIO = BigDecimal(1.5)

    fun calculateBalance(
        players: Players,
        dealer: Dealer,
    ) {
        val dealerMoney = players.fold(BigDecimal.ZERO) { acc, player ->
            val betMoney = calculateMoney(player)
            player.updateBalance(betMoney)
            acc - betMoney
        }
        dealer.updateBalance(dealerMoney)
    }

    private fun calculateMoney(player: Player): BigDecimal {
        return when (player.playerStatus) {
            PlayerStatus.BLACKJACK -> player.initBet.multiply(RATIO)
            PlayerStatus.BUST -> -player.initBet
            PlayerStatus.WIN -> player.initBet
            PlayerStatus.LOSE -> -player.initBet
            PlayerStatus.DRAW -> player.initBet
            else -> player.initBet
        }
    }
}
