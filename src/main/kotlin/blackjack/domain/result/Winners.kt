package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class Winners(players: List<Player>) : Rank(players) {

    companion object {
        fun from(dealer: Dealer, players: List<Player>): Winners {
            val winners = players.filter { player -> player.isWin(dealer) }

            return Winners(winners)
        }
    }
}
