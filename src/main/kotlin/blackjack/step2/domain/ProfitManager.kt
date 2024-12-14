package blackjack.step2.domain

object ProfitManager {
    private const val BLACKJACK_REWARD_RATE = 1.5
    private const val NO_PROFIT = 0.0 // 무승부

    fun calculateFinalProfit(
        participants: List<Participant>,
        betManager: BetManager,
    ): List<ProfitResult> {
        val dealer = participants.filterIsInstance<Dealer>().first()
        val players = participants.filterIsInstance<Player>()

        val playerResults =
            players.map { player ->
                val betAmount = betManager.getBetAmount(player)
                val profit = this.calculatePlayerProfit(player, dealer, betAmount)
                ProfitResult(player, profit)
            }
        val dealerProfit = playerResults.sumOf { -it.profit }
        val dealerResult = ProfitResult(dealer, dealerProfit)

        return playerResults + dealerResult
    }

    private fun calculatePlayerProfit(
        player: Player,
        dealer: Dealer,
        betAmount: Int,
    ): Double {
        if (player.isInitialBlackjack() && dealer.isInitialBlackjack()) {
            return NO_PROFIT // 블랙잭 동시 발생 시 베팅금액 반환
        }
        if (player.isInitialBlackjack() && !dealer.isInitialBlackjack()) {
            return betAmount * BLACKJACK_REWARD_RATE
        }
        if (player.isBust()) return -betAmount.toDouble()
        if (dealer.isBust()) return betAmount.toDouble()

        val playerScore = player.score()
        val dealerScore = dealer.score()
        return when {
            playerScore > dealerScore -> betAmount.toDouble()
            playerScore < dealerScore -> -betAmount.toDouble()
            else -> NO_PROFIT // 무승부
        }
    }
}
