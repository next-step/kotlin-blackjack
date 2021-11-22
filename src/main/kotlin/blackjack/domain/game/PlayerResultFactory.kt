package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object PlayerResultFactory {

    private val gameResults = listOf(
        PlayerBust,
        DealerBust,
        PlayerBlackJack,
        DealerBlackJack,
        PlayerWin,
        PlayerDraw,
        PlayerLose,
    )

    fun getResult(dealer: Dealer, player: Player): GameResult.Type {
        val result = gameResults.first { it.isApplicableTo(dealer, player) }
        return result.getPlayerResult(dealer, player)
    }

    fun getPlayerResult(dealer: Dealer, player: Player): GamerResult {
        val result = getResult(dealer, player)
        return GamerResult(result, player)
    }
}

