package blackjack.service

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class DecisionMatch {

    fun matchWhenFirstCardBlackjack(dealer: Dealer, players: List<Player>) {
        players.forEach { player ->
            dealer.matchWhenFirstCardBlackjack(player)
        }
    }

    fun match(dealer: Dealer, players: List<Player>) {
        players.forEach { player -> dealer.match(player) }
    }
}
