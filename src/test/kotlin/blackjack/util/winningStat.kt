package blackjack.util

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore
import blackjack.domain.winning.WinningStat

object winningStat {

    fun createWinningStat(
        bettingAmount: Int,
        playerScore: Int,
        dealerScore: Int
    ): WinningStat {
        val player = Player("test", bettingAmount)
        return WinningStat(
            listOf(PlayerScore(player, playerScore)),
            PlayerScore(Dealer(), dealerScore)
        )
    }
}
