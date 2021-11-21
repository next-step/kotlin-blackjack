package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object GamerResultFactory {

    private val gameResults = listOf(
        PlayerBust,
        DealerBust,
        PlayerBlackJack,
        DealerBlackJack,
        PlayerWin,
        PlayerDraw,
        PlayerLose,
    )

    fun getGamerResult(dealer: Dealer, player: Player): GamersResult {
        val result = gameResults.first { it.isApplicableTo(dealer, player) }
        return result.get(dealer, player)
    }
}

