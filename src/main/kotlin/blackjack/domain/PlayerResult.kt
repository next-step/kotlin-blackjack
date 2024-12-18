package blackjack.domain

import blackjack.entity.Player

data class PlayerResult(
    val playerName: String,
    val playerProfitAmount: Int,
) {
    fun getResult(): Int {
        return playerProfitAmount
    }

    companion object {
        const val BET_WIN_MULTIPLIER = 1.5

        fun from(
            player: Player,
            isDealerBlackJack: Boolean,
            dealerScore: Int,
            bustLimit: Int,
        ): PlayerResult {
            val playerScore = player.hand.getTotalCardValue()
            val profit = calculateProfit(player, isDealerBlackJack, playerScore, dealerScore, bustLimit)
            return PlayerResult(player.name, profit)
        }

        private fun calculateProfit(
            player: Player,
            isDealerBlackJack: Boolean,
            playerScore: Int,
            dealerScore: Int,
            bustLimit: Int,
        ): Int {
            return when {
                playerScore > bustLimit -> -player.betAmount
                player.isPlayerFirstBlackJack() -> (player.betAmount * BET_WIN_MULTIPLIER).toInt()
                player.isBlackJack() && isDealerBlackJack -> player.betAmount
                playerScore > dealerScore -> player.betAmount
                playerScore == dealerScore -> 0
                else -> -player.betAmount
            }
        }
    }
}
