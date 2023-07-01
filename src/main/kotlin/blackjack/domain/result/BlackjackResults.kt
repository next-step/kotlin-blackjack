package blackjack.domain.result

import blackjack.domain.user.Dealer
import blackjack.domain.user.Users

class BlackjackResults(dealer: Dealer, users: Users) {
    val dealerResult: DealerResult
    val userResults: UserResults

    init {
        val userResultsList = mutableListOf<UserResult>()
        for (user in users) {
            userResultsList.add(UserResult(user, user.match(dealer)))
        }
        userResults = UserResults(userResultsList)
        dealerResult = getDealerResult(dealer, userResults)
    }

    companion object {

        private fun getDealerResult(dealer: Dealer, userResults: UserResults): DealerResult {
            val dealerWinCount = userResults.count { it.result == Result.LOSE }
            val dealerDrawCount = userResults.count { it.result == Result.DRAW }
            val dealerLoseCount = userResults.count() - dealerWinCount - dealerDrawCount
            return DealerResult(dealer, dealerWinCount, dealerDrawCount, dealerLoseCount)
        }
    }
}
