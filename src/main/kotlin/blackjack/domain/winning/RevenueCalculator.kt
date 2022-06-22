package blackjack.domain.winning

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerRevenue
import blackjack.domain.player.PlayerRevenues

class RevenueCalculator(private val winningStat: WinningStat) {
    private var dealerRevenue: Double = 0.0

    fun calculate(): PlayerRevenues {
        val playersRevenue = winningStat.result.map {
            when (it.playerWinningState) {
                WinningState.DEALER_BUST -> calculatePlayerRevenue(it.player)
                WinningState.PLAYER_BUST -> calculatePlayerBust(it.player)
                WinningState.PLAYER_BLACKJACK -> calculatePlayerBlackJack(it.player)
                else -> comparePlayer(it.player, it.playerWinningState)
            }
        }
        return PlayerRevenues(
            dealerRevenue,
            playersRevenue
        )
    }

    private fun calculatePlayerRevenue(player: Player): PlayerRevenue {
        return PlayerRevenue(player, player.bettingAmount.toDouble())
    }

    private fun calculatePlayerBust(player: Player): PlayerRevenue {
        dealerRevenue += player.bettingAmount.toDouble()
        return PlayerRevenue(player, -player.bettingAmount.toDouble())
    }

    private fun calculatePlayerBlackJack(player: Player): PlayerRevenue {
        val revenue = player.bettingAmount.toDouble()
        dealerRevenue -= (revenue * BLACK_JACK_BENEFIT)
        return PlayerRevenue(player, revenue * BLACK_JACK_BENEFIT)
    }

    private fun comparePlayer(
        player: Player,
        playerWinningState: WinningState
    ): PlayerRevenue {
        val revenue = player.bettingAmount.toDouble()
        return when (playerWinningState) {
            WinningState.PLAYER_WIN -> {
                dealerRevenue -= revenue
                PlayerRevenue(player, revenue)
            }
            WinningState.PLAYER_LOOSE -> {
                dealerRevenue += revenue
                PlayerRevenue(player, -revenue)
            }
            else -> PlayerRevenue(player, revenue)
        }
    }

    companion object {
        private const val BLACK_JACK_BENEFIT = 1.5
    }
}
