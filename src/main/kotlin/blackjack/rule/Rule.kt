package blackjack.rule

import blackjack.dealer.Dealer
import blackjack.player.Players

object Rule {
    fun isGameActive(players: Players, dealer: Dealer) = !dealer.isBust() && players.players.any { !it.isBust() }
}