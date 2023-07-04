package blackjack.domain.result

import blackjack.domain.user.Dealer
import blackjack.domain.user.User
import blackjack.domain.user.Users

class BlackjackResults(dealer: Dealer, users: Users) {
    val dealerResult: PlayerResult
    val userResults: PlayerResults

    init {
        val playerResultsList = mutableListOf<PlayerResult>()
        var dealerProfit = 0
        for (user in users) {
            val userProfit = getUserProfit(user, user.match(dealer))
            dealerProfit -= userProfit
            playerResultsList.add(PlayerResult(user, userProfit))
        }
        userResults = PlayerResults(playerResultsList)
        dealerResult = PlayerResult(dealer, dealerProfit)
    }

    companion object {

        private fun getUserProfit(user: User, matchResult: MatchResult): Int {
            return when (matchResult) {
                MatchResult.BLACKJACK_WIN -> (user.betMoney * 1.5).toInt()
                MatchResult.WIN -> user.betMoney
                MatchResult.DRAW -> 0
                MatchResult.LOSE -> -user.betMoney
            }
        }
    }
}
