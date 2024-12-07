package blackjack.entity

import blackjack.entity.GameRules.BLACK_JACK_BETTING_RATE

class BettingAmount(val amount: Int) {
    init {
        require(amount > 0) { "베팅 금액은 0보다 커야합니다." }
    }

    fun winBlackjack(): Int = (amount * BLACK_JACK_BETTING_RATE).toInt()

    fun winBet() = amount

    fun loseBet() = -amount
}
