package blackjack.domain

import blackjack.entity.Player

data class PlayerResult(
    val playerName: String,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int,
) {
    fun getResult(): String {
        return when {
            winCount > WIN_COMPARE_COUNT -> WIN_RETURN_VALUE
            else -> LOSE_RETURN_VALUE
        }
    }

    companion object {
        private const val WIN_COMPARE_COUNT = 0
        private const val WIN_RETURN_VALUE = "승"
        private const val LOSE_RETURN_VALUE = "패"

        fun from(
            player: Player,
            dealerScore: Int,
            bustLimit: Int,
        ): PlayerResult {
            val playerScore = player.hand.getTotalCardValue()

            return when {
                playerScore > bustLimit ->
                    PlayerResult(
                        player.name,
                        winCount = 0,
                        loseCount = 1,
                        drawCount = 0,
                    )

                playerScore > dealerScore ->
                    PlayerResult(
                        player.name,
                        winCount = 1,
                        loseCount = 0,
                        drawCount = 0,
                    )

                playerScore == dealerScore ->
                    PlayerResult(
                        player.name,
                        winCount = 0,
                        loseCount = 0,
                        drawCount = 1,
                    )

                else -> PlayerResult(player.name, winCount = 0, loseCount = 1, drawCount = 0)
            }
        }
    }
}
