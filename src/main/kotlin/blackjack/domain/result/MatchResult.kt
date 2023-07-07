package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class MatchResult(val player: Player, val dealer: Dealer) {
    fun ofPlayer(): Result {
        val playerScore = player.score().value
        val dealerScore = dealer.score().value
        return when {
            dealer.isBust() -> Result.WIN
            player.isBust() -> Result.LOSE
            dealerScore < playerScore -> Result.WIN
            playerScore < dealerScore -> Result.LOSE
            else -> Result.DRAW
        }
    }

    fun ofDealer(): Result {
        return ofPlayer().opposite()
    }
}
