package blackjack.domain

import blackjack.domain.enums.Condition

class Player(
    name: String,
    cards: Cards,
    condition: Condition = Condition.PLAY,
    betAmount: Int,
) : Participant(name, cards, condition) {

    var betAmount: Int = betAmount
        private set

    fun currentCondition(): Condition {
        return this.condition
    }

    fun changeCondition(condition: Condition) {
        this.condition = condition
    }

    fun loseAllBets() {
        this.betAmount = 0
    }
}
