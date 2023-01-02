package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.player.betting.Betting
import blackjack.domain.player.betting.Profit
import blackjack.domain.player.betting.ProfitRate
import blackjack.domain.player.betting.times
import blackjack.domain.player.result.PlayerResult

class Player(
    name: String,
    cards: Cards,
    betting: Double = DEFAULT_BETTING_AMOUNT
) : CardHolder(name, cards) {

    private val betting: Betting = Betting(betting)

    private var playerResult: PlayerResult = PlayerResult.NOT_FINISHED

    val finalResult: PlayerResult
        get() = playerResult

    init {
        require(betting > 0) {
            "Betting amount must be greater than 0"
        }
    }

    fun takeResult(otherCards: Cards): PlayerResult {
        checkFinished()

        val playerResult = calculateResult(otherCards)

        saveResult(playerResult)

        return playerResult
    }

    fun getProfit(): Profit {
        return betting * getProfitRate()
    }

    private fun calculateResult(otherCards: Cards): PlayerResult =
        PlayerResult.of(cards, otherCards)

    private fun saveResult(playerResult: PlayerResult) {
        this.playerResult = playerResult
    }

    private fun getProfitRate(): ProfitRate {
        require(finalResult != PlayerResult.NOT_FINISHED) {
            "Player should take result before getting profit"
        }

        return when {
            isWin() && isBlackjack() -> ProfitRate.BLACKJACK_WIN_PROFIT_RATE
            isDraw() && isBlackjack() -> ProfitRate.BLACKJACK_DRAW_PROFIT_RATE
            isWin() -> ProfitRate.WIN_PROFIT_RATE
            isDraw() -> ProfitRate.DRAW_PROFIT_RATE
            isLose() && isNotBust() -> ProfitRate.LOSE_PROFIT_RATE
            isLose() && isBust() -> ProfitRate.BUST_PROFIT_RATE
            else -> throw IllegalStateException("Not supported")
        }
    }

    private fun isWin(): Boolean = finalResult == PlayerResult.WIN

    private fun isDraw(): Boolean = finalResult == PlayerResult.DRAW

    private fun isLose(): Boolean = finalResult == PlayerResult.LOSE

    companion object {
        private const val DEFAULT_BETTING_AMOUNT: Double = 1000.0
    }
}
