package blackjack.domain.participant

import blackjack.domain.game.Rank

class BettingPlayer(val player: Player, private val amount: Int) {

    fun getRevenue(dealer: Dealer): Int {
        return amount * (Rank.of(player, dealer).value).toInt()
    }
}
