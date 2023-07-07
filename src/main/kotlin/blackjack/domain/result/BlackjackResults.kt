package blackjack.domain.result

import blackjack.domain.user.Dealer
import blackjack.domain.user.Users

class BlackjackResults(dealer: Dealer, users: Users) {
    val dealerResult: PlayerResult
    val userResults: PlayerResults

    init {
        val playerResultsList = mutableListOf<PlayerResult>()
        var dealerProfit = 0
        for (user in users) {
            val userProfit = user.getUserProfit(dealer)
            dealerProfit -= userProfit
            playerResultsList.add(PlayerResult(user, userProfit))
        }
        userResults = PlayerResults(playerResultsList)
        dealerResult = PlayerResult(dealer, dealerProfit)
    }
}
