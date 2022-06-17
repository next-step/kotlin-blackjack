package blackjack.dto

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class BlackJackRequest(val players: List<Player>, val dealer: Dealer) {
    companion object {

        fun of(players: List<String>): BlackJackRequest {
            return BlackJackRequest(
                players.map {
                    Player(it)
                },
                Dealer()
            )
        }
    }
}
