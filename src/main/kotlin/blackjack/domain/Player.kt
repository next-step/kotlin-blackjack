package blackjack.domain

import blackjack.domain.enums.Condition

class Player(
    name: String,
    cards: Cards,
    override var condition: Condition = Condition.PLAY
) : Participant(name, cards, condition) {

    fun currentCondition(): Condition {
        return this.condition
    }

    fun changeCondition(condition: Condition) {
        this.condition = condition
    }
}
