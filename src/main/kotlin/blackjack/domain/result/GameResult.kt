package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class GameResult(private val winners: Winners, private val losers: Losers) {
    operator fun component1() = winners
    operator fun component2() = losers

    companion object {

        fun from(dealer: Dealer, players: List<Player>): GameResult {
            val winners = Winners.from(dealer, players)
            val losers = Losers.from(dealer, players)

            return GameResult(winners, losers)
        }
    }
}
