package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class Winner(
    private val dealer: Dealer,
    private val players: List<Player>
) {

    fun isWin(player: Player): Boolean {
        if (dealer.isBust()) return true

        return player.point > dealer.point
    }

    fun gameResult(): GameResult {
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
