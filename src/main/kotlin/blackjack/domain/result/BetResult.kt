package blackjack.domain.result

import blackjack.domain.participant.BetAmount

class BetResult private constructor(val result: Result, val earningAmount: Double) {
    constructor(result: Result, earningAmount: Int) : this(result, earningAmount.toDouble())

    fun opposite(): BetResult {
        return BetResult(result = result.opposite(), earningAmount = -earningAmount)
    }

    companion object {
        fun ofBlackJackWin(betAmount: BetAmount) = BetResult(Result.WIN, betAmount.value * 1.5)
        fun ofBlackJackDraw(betAmount: BetAmount) = BetResult(Result.DRAW, betAmount.value)
        fun ofWin(betAmount: BetAmount) = BetResult(Result.WIN, betAmount.value)
        fun ofLose(betAmount: BetAmount) = BetResult(Result.LOSE, -betAmount.value)
        fun ofDraw() = BetResult(Result.DRAW, 0)
    }
}
