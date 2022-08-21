package blackjack.domain.GameProfit

import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import java.math.BigDecimal

@JvmInline
value class GameProfit(
    val value: BigDecimal
) {
    operator fun not(): GameProfit {
        return GameProfit(value.negate())
    }

    operator fun plus(other: GameProfit): GameProfit {
        return GameProfit(value.add(other.value))
    }

    companion object {
        private val NONE = GameProfit(BigDecimal.ZERO)
        private const val SCORE_LIMIT = 21
        private const val BLACKJACK_EARNING_RATE = 1.5

        fun valueOf(player: Player, dealer: Dealer): GameProfit {
            if (isDealerWin(player, dealer)) {
                return !of(player.bettingMoney)
            }

            if (isPlayerWin(player, dealer)) {
                return getPlayerProfit(player, dealer)
            }

            return NONE
        }

        private fun of(bettingMoney: BettingMoney): GameProfit {
            return GameProfit(bettingMoney.value)
        }

        private fun getPlayerProfit(player: Player, dealer: Dealer): GameProfit {
            if (player.isBlackjack()) {
                return decidePlayerWinOrDraw(player, dealer)
            }

            return of(player.bettingMoney)
        }

        private fun decidePlayerWinOrDraw(player: Player, dealer: Dealer): GameProfit {
            if (dealer.isBlackjack()) {
                return NONE
            }

            return GameProfit(player.bettingMoney.value * BLACKJACK_EARNING_RATE)
        }

        private fun isDealerWin(player: Player, dealer: Dealer): Boolean {
            val playerScore = player.score
            if (playerScore > SCORE_LIMIT) {
                return true
            }

            val dealerScore = dealer.score
            if (dealerScore > SCORE_LIMIT || dealerScore <= playerScore) {
                return false
            }

            return true
        }

        private fun isPlayerWin(player: Player, dealer: Dealer): Boolean {
            val playerScore = player.score
            if (playerScore > SCORE_LIMIT) {
                return false
            }

            val dealerScore = dealer.score
            if (dealerScore > SCORE_LIMIT || dealerScore < playerScore) {
                return true
            }

            return false
        }
    }
}

private operator fun BigDecimal.times(multiplier: Double): BigDecimal {
    return this.multiply(BigDecimal.valueOf(multiplier))
}
