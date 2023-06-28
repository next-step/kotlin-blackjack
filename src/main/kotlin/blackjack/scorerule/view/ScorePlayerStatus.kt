package blackjack.scorerule.view

import blackjack.scorerule.domain.ScoreBoard
import blackjack.scorerule.domain.ScorePlayer

data class ScorePlayerStatus(
    val name: String,
    val handRepresent: String,
    val optimalValue: Int,
    val scoreBoard: ScoreBoard
) {
    companion object {
        fun of(player: ScorePlayer): ScorePlayerStatus {
            return ScorePlayerStatus(player.name, player.showHands(), player.optimalValue(), player.scoreBoard())
        }

        fun dealerUpCard(dealer: ScorePlayer): ScorePlayerStatus {
            return ScorePlayerStatus(dealer.name, dealer.hands().first().representCard(), dealer.optimalValue(), dealer.scoreBoard())
        }
    }
}
