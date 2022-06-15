package blackjack.domain

class BetResult(val user: User, betMoney: Money, resultState: ResultStatus = ResultStatus.None) {
    val earnMoney = betMoney * resultState.earnMoneyRatio
}

fun List<BetResult>.sumOfEarnMoney(): Money {
    return fold(Money(0)) { acc, result -> acc + result.earnMoney }
}
