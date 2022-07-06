package camp.nextstep.blackjack.ui

import camp.nextstep.blackjack.game.Score
import camp.nextstep.blackjack.player.Player

class PlayerScore(player: Player, score: Score) {
    val playerHand = PlayerHand(player)
    val score = score.value

    override fun toString(): String {
        return "$playerHand - 결과 : $score"
    }
}
