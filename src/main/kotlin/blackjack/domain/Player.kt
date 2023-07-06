package blackjack.domain

import blackjack.domain.enums.Condition

class Player(
    name: String,
    cards: Cards,
    condition: Condition = Condition.PLAY,
    betAmount: Double,
) : Participant(name, cards, condition) {

    var betAmount: Double = betAmount
        private set

    fun currentCondition(): Condition {
        return this.condition
    }

    fun changeCondition(condition: Condition) {
        this.condition = condition
    }

    fun loseAllBets() {
        this.betAmount = 0.0
    }

    fun blackjack() {
        betAmount *= BLACKJACK_BET_RATE
    }

    companion object {
        private const val BLACKJACK_BET_RATE = 1.5
    }
}
