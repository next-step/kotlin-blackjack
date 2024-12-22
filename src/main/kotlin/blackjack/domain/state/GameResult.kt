package blackjack.domain.state

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser

class GameResult(dealer: Dealer, gameUsers: List<GameUser>) {
    val usersResult: Map<GameUser, ResultState> =
        gameUsers.associateWith {
            dealer.compareGetResultOpponent(it)
        }

    val dealerBalance =
        usersResult.map { (user, result) ->
            user.bettingRevenue(result) * -1
        }.sum()
}
