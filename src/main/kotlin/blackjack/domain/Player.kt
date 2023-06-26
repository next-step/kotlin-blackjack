package blackjack.domain

import blackjack.domain.enums.Condition

class Player(
    val name: String,
    val cards: Cards,
    private var condition: Condition = Condition.PLAY
) {
    fun hit(card: Card) {
        this.cards.append(card)
    }

    fun currentCondition(): Condition {
        return this.condition
    }

    fun changeCondition(condition: Condition) {
        this.condition = condition
    }
}
