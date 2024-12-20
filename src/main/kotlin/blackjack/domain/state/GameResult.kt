package blackjack.domain.state

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser

class GameResult(dealer: Dealer, gameUsers: List<GameUser>) {
    var dealerBalance = 0
        private set
    val usersResult: Map<GameUser, ResultState> =
        gameUsers.associateWith {
            val userResultState = dealer.comparePoints(it).not(it.isBlackJack())
            dealerBalance -= it.bettingRevenue(userResultState)
            userResultState
        }
}
