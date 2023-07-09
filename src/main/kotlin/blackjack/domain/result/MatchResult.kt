package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class MatchResult(val player: Player, val dealer: Dealer) {
    fun ofPlayer(): BetResult {
        val playerScore = player.score().value
        val dealerScore = dealer.score().value
        return when {
            player.isBlackJack() && !dealer.isBlackJack() -> BetResult.ofBlackJackWin(player.betAmount)
            player.isBlackJack() && dealer.isBlackJack() -> BetResult.ofBlackJackDraw(player.betAmount)
            dealer.isBust() -> BetResult.ofWin(player.betAmount)
            player.isBust() -> BetResult.ofLose(player.betAmount)
            dealerScore < playerScore -> BetResult.ofWin(player.betAmount)
            playerScore < dealerScore -> BetResult.ofLose(player.betAmount)
            else -> BetResult.ofDraw()
        }
    }

    fun ofDealer(): BetResult {
        return ofPlayer().opposite()
    }
}
