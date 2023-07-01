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
            val userResultsCountBy: Map<Result, Int> = userResults.groupingBy { it.result }.eachCount()
            return DealerResult(
                dealer,
                userResultsCountBy.getOrDefault(Result.LOSE, 0),
                userResultsCountBy.getOrDefault(Result.DRAW, 0),
                userResultsCountBy.getOrDefault(Result.WIN, 0),
            )
        }
    }
}
