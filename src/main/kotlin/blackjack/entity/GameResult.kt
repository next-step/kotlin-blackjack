package blackjack.entity

data class GameResult(
    val player: Participant,
    val earning: Int = 0,
) {
    companion object {
        fun draw(player: Participant): GameResult {
            return GameResult(player)
        }

        fun blackjackWin(
            player: Participant,
            bettingAmount: BettingAmount,
        ): GameResult {
            return GameResult(player, earning = bettingAmount.winBlackjack())
        }

        fun win(
            player: Participant,
            bettingAmount: BettingAmount,
        ): GameResult {
            return GameResult(player, earning = bettingAmount.winBet())
        }

        fun lose(
            player: Participant,
            bettingAmount: BettingAmount,
        ): GameResult {
            return GameResult(player, earning = bettingAmount.loseBet())
        }
    }
}
