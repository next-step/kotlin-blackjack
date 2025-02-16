package blackjack.domain

import java.math.BigDecimal

enum class ResultStatus(val profitRate: BigDecimal) {
    BLACKJACK_WIN(BigDecimal(1.5)),
    WIN(BigDecimal(1.0)),
    DRAW(BigDecimal(1.0)),
    DEFEAT(BigDecimal(-1.0)), ;

    companion object {
        fun of(gambler: Gambler, dealer: Dealer): ResultStatus {
            if (dealer.isBlackjack()) {
                return when {
                    gambler.isBlackjack() -> DRAW
                    else -> DEFEAT
                }
            }

            if (dealer.isBurst()) {
                return WIN
            }

            return when {
                gambler.isBlackjack() -> BLACKJACK_WIN
                gambler.isBurst() -> DEFEAT
                gambler.isScoreLargerThan(dealer) -> WIN
                gambler.isScoreEqualTo(dealer) -> DRAW
                else -> DEFEAT
            }
        }
    }
}
