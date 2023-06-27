package next.step.blackjack.domain.betting

import next.step.blackjack.domain.game.GameOdds

@JvmInline
value class BettingAmount(private val amount: Int) {
    init {
        require(amount > 0) { "베팅 금액은 0보다 커야 합니다." }
    }

    operator fun times(odds: GameOdds): Int = (this.amount * odds.odds).toInt()

    companion object {
        fun of(amount: Int): BettingAmount = BettingAmount(amount)
    }
}