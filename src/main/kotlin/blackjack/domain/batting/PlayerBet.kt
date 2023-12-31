package blackjack.domain.batting

import blackjack.domain.player.PlayerName
import blackjack.domain.result.game.Profit

sealed interface PlayerBet {
    val playerName: PlayerName

    data class Placed(
        override val playerName: PlayerName,
        val betAmount: BetAmount,
    ) : PlayerBet

    data class Finished(
        override val playerName: PlayerName,
        val betAmount: BetAmount,
        val payoutAmount: Amount,
    ) : PlayerBet {

        val profit: Profit = Profit.of(betAmount.value, payoutAmount)
        companion object {
            fun of(betPlaced: Placed, payoutAmount: Amount): Finished = Finished(
                playerName = betPlaced.playerName,
                betAmount = betPlaced.betAmount,
                payoutAmount = payoutAmount
            )
        }
    }
}
