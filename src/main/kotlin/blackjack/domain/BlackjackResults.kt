package blackjack.domain

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

enum class Result {
    WIN, DRAW, LOSE
}

data class DealerResult(val dealer: Dealer, val winCount: Int, val drawCount: Int, val loseCount: Int)

data class UserResults(private val userResults: List<UserResult>) : Iterable<UserResult> by userResults

data class UserResult(val user: User, val result: Result)
