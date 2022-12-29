package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class GameResult private constructor(val winnerCount: Int, val loseCount: Int) {

    companion object {
        fun from(dealer: Dealer, players: List<Player>): GameResult {
            val winnerCount = players.count { player ->
                dealer.point > player.point
            }

            val loseCount = players.count { player ->
                dealer.point < player.point
            }

            return GameResult(
                winnerCount = if (dealer.isBust()) 0 else winnerCount,
                loseCount = if (dealer.isBust()) players.size else loseCount
            )
        }
    }
}
