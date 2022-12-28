package blackjack.domain

data class PlayerResult(val user: User, val result: ResultStatus, val profit: Profit) {
    companion object {
        fun of(user: User, dealer: Dealer): PlayerResult {
            val userResult = dealer.getMatchResult(user)
            val userProfit = ProfitCalculator().calculate(user, userResult)

            return PlayerResult(user, userResult, userProfit)
        }
    }
}
