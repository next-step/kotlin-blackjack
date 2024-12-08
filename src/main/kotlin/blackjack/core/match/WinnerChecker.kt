package blackjack.core.match

import blackjack.core.player.Dealer
import blackjack.core.player.Player

object WinnerChecker {
    fun check(
        dealer: Dealer,
        player: Player,
    ): MatchResult {
        return when {
            dealer.checkBust() -> MatchResult.LOSE
            player.checkBust() -> MatchResult.WIN
            dealer.point() > player.point() -> MatchResult.WIN
            dealer.point() < player.point() -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }
}
