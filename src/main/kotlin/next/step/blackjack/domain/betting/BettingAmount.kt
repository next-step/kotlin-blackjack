package next.step.blackjack.domain.betting

import next.step.blackjack.domain.game.GameOdds

@JvmInline
value class BettingAmount(private val amount: Int) {
    init {
        require(amount > MINIMUM_BETTING_AMOUNT) { "베팅 금액은 0보다 커야 합니다." }
    }

    operator fun times(odds: GameOdds): Int = (this.amount * odds.odds).toInt()

    companion object {
        private const val MINIMUM_BETTING_AMOUNT = 0

        fun of(amount: Int): BettingAmount = BettingAmount(amount)
    }
}
