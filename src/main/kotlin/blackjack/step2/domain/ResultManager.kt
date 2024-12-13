package blackjack.step2.domain

object ResultManager {
    private const val BLACKJACK_MULTIPLIER = 1.5
    private const val ZERO = 0.0

    fun calculateFinalProfit(participants: List<Participant>): List<GameResult> {
        val dealer =
            participants.filterIsInstance<Dealer>().firstOrNull()
                ?: throw IllegalArgumentException("딜러가 리스트에 포함되어야 합니다.")
        val players = participants.filterIsInstance<Player>()

        val playerResults =
            players.map { player ->
                val profit = this.calculatePlayerProfit(player, dealer)
                GameResult(player, profit)
            }
        val dealerProfit = playerResults.sumOf { -it.profit }
        val dealerResult = GameResult(dealer, dealerProfit)

        return playerResults + dealerResult
    }

    private fun calculatePlayerProfit(
        player: Player,
        dealer: Dealer,
    ): Double {
        if (player.isInitialBlackjack() && dealer.isInitialBlackjack()) {
            return ZERO // 블랙잭 동시 발생 시 베팅금액 반환
        }
        if (player.isInitialBlackjack() && !dealer.isInitialBlackjack()) {
            return player.bet * BLACKJACK_MULTIPLIER
        }
        if (player.isBust()) return -player.bet.toDouble()
        if (dealer.isBust()) return player.bet.toDouble()

        val playerScore = player.score()
        val dealerScore = dealer.score()
        return when {
            playerScore > dealerScore -> player.bet.toDouble()
            playerScore < dealerScore -> -player.bet.toDouble()
            else -> ZERO // 무승부
        }
    }
}
