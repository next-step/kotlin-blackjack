package model

import kotlin.math.roundToInt

class ProfitCalculator {
    fun run(dealer: Dealer, player: Participant): Int {
        var profitResult = 0
        val dealerTotalNumber: Int = CardNumberCalculator.sumCardNumbers(dealer.cards)
        val dealerScoreState: ScoreState = dealer.scoreState
        val playerTotalNumber: Int = CardNumberCalculator.sumCardNumbers(player.cards)
        val playerScoreState: ScoreState = player.scoreState

        if (isBlackJackViaDealOut(playerScoreState, player.cards.size)) {
            return (player.bettingMoney * BLACKJACK_PROFIT_RATIO).roundToInt()
        }

        when {
            (dealerScoreState == ScoreState.BUST) -> {
                profitResult = player.bettingMoney
            }

            (playerScoreState == ScoreState.BUST) -> {
                profitResult = -player.bettingMoney
            }

            (dealerScoreState == ScoreState.BLACKJACK && playerScoreState == ScoreState.BLACKJACK) -> {
                profitResult = 0
            }

            (dealerScoreState == ScoreState.BLACKJACK) -> {
                profitResult = -player.bettingMoney
            }

            (playerScoreState == ScoreState.BLACKJACK) -> {
                profitResult = player.bettingMoney
            }

            (dealerTotalNumber > playerTotalNumber) -> {
                profitResult = -player.bettingMoney
            }

            (dealerTotalNumber < playerTotalNumber) -> {
                profitResult = player.bettingMoney
            }
        }
        return profitResult
    }

    private fun isBlackJackViaDealOut(playerScoreState: ScoreState, player: Int): Boolean {
        return (playerScoreState == ScoreState.BLACKJACK && player == BLACKJACK_VIA_DEAL_OUT)
    }

    companion object {
        private const val BLACKJACK_PROFIT_RATIO = 1.5
        private const val BLACKJACK_VIA_DEAL_OUT = 2
    }
}
