package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player

class GameResultChecker(
    private val dealer: Dealer
) {
    fun determineWinner(playerValue: Int, dealerValue: Int): Winner {
        return when {
            playerValue > 21 -> Winner.DEALER
            dealerValue > 21 -> Winner.PLAYER
            playerValue > dealerValue -> Winner.PLAYER
            playerValue < dealerValue -> Winner.DEALER
            else -> Winner.DRAW
        }
    }

    fun updateScores(winner: Winner, player: Player) {
        when (winner) {
            Winner.PLAYER -> {
                player.scoreBoard().winTo(dealer.scoreBoard())
            }
            Winner.DEALER -> {
                player.scoreBoard().loseTo(dealer.scoreBoard())
            }
            Winner.DRAW -> {
                player.scoreBoard().draw(dealer.scoreBoard())
            }
        }
    }

    enum class Winner {
        PLAYER, DEALER, DRAW
    }
}
