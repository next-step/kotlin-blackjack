package blackjack

class BlackjackPlayerProfitCalculatorHandler() {
    fun findCalculator(playerResult: PlayerResult): BlackjackPlayerProfitCalculator {
        return when (playerResult) {
            PlayerResult.WIN -> BlackjackWinnerProfitCalculator()
            PlayerResult.LOSS -> BlackjackLoserProfitCalculator()
            PlayerResult.PUSH -> BlackjackPushPlayerProfitCalculator()
        }
    }
}
