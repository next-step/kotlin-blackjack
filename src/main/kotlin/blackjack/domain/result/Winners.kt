package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Player

class Winners(
    private val players: List<Player>
) {

    fun exist(name: String): Boolean =
        players.any { player -> player.name == name }

    companion object {
        fun from(dealer: Dealer, players: List<Player>): Winners {
            val winners = players.filter { player ->
                player.point > dealer.point
            }

            return Winners(winners)
        }
    }
}
