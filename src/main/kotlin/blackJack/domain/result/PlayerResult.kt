package blackJack.domain.result

import blackJack.domain.enums.BlackjackResult
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

class PlayerResult(val name: String, val profit: Int) {
    companion object {
        fun calculateResult(player: Player, dealer: Dealer): PlayerResult {
            val dealerScore = dealer.cards.calculateTotalScore()
            val playerScore = player.cards.calculateTotalScore()

            val result = when {
                player.isBlackJack() && dealer.isBlackJack() -> BlackjackResult.DRAW
                player.isBlackJack() -> BlackjackResult.BLACKJACK
                dealer.isBlackJack() -> BlackjackResult.LOSE
                playerScore > 21 -> BlackjackResult.LOSE
                dealerScore > 21 -> BlackjackResult.WIN
                playerScore > dealerScore -> BlackjackResult.WIN
                playerScore == dealerScore -> BlackjackResult.DRAW
                else -> BlackjackResult.LOSE
            }
            val profit: Int = (player.bettingPrice * result.reward).toInt()

            return PlayerResult(player.name, profit)
        }
    }
}
