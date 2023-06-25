package blackjack.domain

import blackjack.enums.Condition

class Player(
    val name: String,
    var cards: MutableList<Card>,
    var condition: Condition = Condition.YES
) {
    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun changeCondition(condition: String) {
        this.condition = Condition.values().first { it.condition == condition}
    }
}
