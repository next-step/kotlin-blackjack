package blackjack.view

import blackjack.domain.Player
import blackjack.domain.ScoreBoard

data class PlayerStatus(
    val name: String,
    val handRepresent: String,
    val optimalValue: Int,
    val scoreBoard: ScoreBoard
) {
    companion object {
        fun of(player: Player): PlayerStatus {
            return PlayerStatus(player.name, player.showHands(), player.optimalValue(), player.scoreBoard())
        }

        fun upCard(dealer: Player): PlayerStatus {
            return PlayerStatus(dealer.name, dealer.hands().first().representCard(), dealer.optimalValue(), dealer.scoreBoard())
        }
    }
}
