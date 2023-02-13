package model

import kotlin.math.roundToInt

class ProfitCalculator {
    fun getProfitResult(player: Participant): Int {
        var profitResult: Int
        if (player.scoreState == ScoreState.BLACKJACK && player.cards.size == 2) {
            profitResult = (player.bettingMoney * BLACKJACK_PROFIT_RATIO).roundToInt()
            return profitResult
        }
        profitResult = when (player.gameResultState) {
            GameResultState.LOSE -> -player.bettingMoney
            GameResultState.DRAW -> 0
            GameResultState.WIN -> player.bettingMoney
        }
        return profitResult
    }

    companion object {
        private const val BLACKJACK_PROFIT_RATIO = 1.5
    }
}
