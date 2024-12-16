package blackjack.domain

import java.math.BigDecimal

enum class GameResult {
    WIN,
    BUST,
    LOSE,
    PUSH,
    BLACK_JACK,
    ;

    fun getBetMoneyAmount(betMoney: BetMoney): BigDecimal {
        return when (this) {
            BLACK_JACK -> betMoney.getAmountOnBlackJack()
            WIN -> betMoney.getOriginalBetAmount()
            PUSH -> betMoney.getOriginalBetAmount()
            LOSE -> betMoney.getAmountOnLose()
            BUST -> betMoney.getAmountOnBust()
        }
    }

    companion object {
        fun fromScores(
            dealerScore: Int,
            playerScore: Int,
        ): GameResult {
            return when {
                dealerScore > Card.MAX_SUM -> WIN // Dealer bust
                playerScore > Card.MAX_SUM -> BUST // Player bust
                dealerScore > playerScore -> LOSE
                playerScore > dealerScore -> WIN
                else -> PUSH
            }
        }
    }
}
