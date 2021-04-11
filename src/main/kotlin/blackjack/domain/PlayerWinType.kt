package blackjack.domain

import java.math.BigDecimal
import java.math.RoundingMode

enum class PlayerWinType(
    private val profitRatio: BigDecimal
) {
    WIN(BigDecimal("1")), LOSE(BigDecimal("-1")), DRAW(BigDecimal("0")), BLACKJACK(BigDecimal("1.5"));

    fun calculateProfit(betAmount: Int): BigDecimal {
        return BigDecimal(betAmount).multiply(profitRatio).setScale(0, RoundingMode.FLOOR)
    }

    companion object {
        fun findPlayerWinType(playerPoint: PlayerPoint, dealerPoint: PlayerPoint): PlayerWinType {
            if (playerPoint.isBlackjack && !dealerPoint.isBlackjack) {
                return BLACKJACK
            }

            if (playerPoint.point > Player.BLACK_JACK_TWENTY_ONE
                || (dealerPoint.point > playerPoint.point && dealerPoint.point <= Player.BLACK_JACK_TWENTY_ONE)) {
                return LOSE
            }

            if (dealerPoint.point > Player.BLACK_JACK_TWENTY_ONE || dealerPoint.point < playerPoint.point) {
                return WIN
            }

            return DRAW
        }


    }
}
