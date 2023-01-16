package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class Losers(private val players: List<Player>) : Rank(players) {

    companion object {
        fun from(dealer: Dealer, players: List<Player>): Losers {
            val losers = players.filter { player -> player.isLose(dealer) }

            return Losers(losers)
        }
    }

    fun settle(): List<Player> =
        players.map { player ->
            player.losePrice()
            player
        }
}
