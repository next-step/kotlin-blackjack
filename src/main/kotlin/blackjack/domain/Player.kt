package blackjack.domain

import blackjack.enums.Condition

class Player(
    val name: String,
    val cards: Cards,
    var condition: Condition = Condition.YES
) {
    fun hit(card: Card) {
        this.cards.hitCard(card)
    }

    fun changeCondition(condition: String) {
        this.condition = Condition.values().first { it.condition == condition }
    }
}
