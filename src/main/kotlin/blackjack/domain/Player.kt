package blackjack.domain

import blackjack.domain.enums.Condition

class Player(
    name: String,
    cards: Cards,
    condition: Condition = Condition.PLAY,
    val betAmount: Double,
) : Participant(name, cards, condition) {

    var money: Double = betAmount
        private set

    fun currentCondition(): Condition {
        return this.condition
    }

    fun changeCondition(condition: Condition) {
        this.condition = condition
    }

    fun loseAllBets() {
        this.money = 0.0
    }

    fun blackjack() {
        money = this.betAmount * BLACKJACK_BET_RATE
    }

    companion object {
        private const val BLACKJACK_BET_RATE = 1.5
    }
}
